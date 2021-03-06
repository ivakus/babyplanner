package ru.c0ner.babyplaner;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ru.c0ner.babyplaner.Fragments.Budjet;
import ru.c0ner.babyplaner.Fragments.BudjetItems;
import ru.c0ner.babyplaner.Fragments.Setting;
import ru.c0ner.babyplaner.Fragments.Sumki;
import ru.c0ner.babyplaner.Fragments.SumkiItems;
import ru.c0ner.babyplaner.Fragments.babyAddDialog;
import ru.c0ner.babyplaner.Fragments.babyBudjetItem;
import ru.c0ner.babyplaner.Fragments.babyFragment;
import ru.c0ner.babyplaner.Fragments.babyItemBase;
import ru.c0ner.babyplaner.Fragments.babyItemBudjet;
import ru.c0ner.babyplaner.Fragments.babyStoradge;
import ru.c0ner.babyplaner.Fragments.dialogDataReturn;
import ru.c0ner.babyplaner.base.babyDBHelper;
import ru.c0ner.babyplaner.base.babyDataSet;

public class app_main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , babyFragment.onItemSelectListiner, FloatingActionButton.OnClickListener, babyAddDialog.babyDialogreturnListener {

    final String STORADGE_NAME = "baby_v1"; // Имя параметра версии хранилища
    final String USER_NAME = "USER_NAME";   //клюя для параметра имя пользователя
    final String FIRST_START = "FIRST_START"; //ключ для доступа в хранилице для параметра первый запуск
    boolean isFirst = true;  // сюда загружаем данные из хранилища, параметр первый запуск
    final String DAYS_RODOV = "DAYS_RODOV";  //ключ для доступа в хранилище , параметр дата родов
    final String DAYS_RODOV_LONG = "DAYS_RODOV_LONG";

    long mDate_rodov; // дата родов в милиисекундах
    int days_rodov_int = 180;   //храним данные по дате родов для обновления прогресс бара в навигационном меню
    String mUser_name = "Гость";  // тут храним имя пользователя
    Budjet budjet;    //фрагмент обрабатывающий список бюджетов
    Setting mSetting;// фрагмент обрабатывающий настройки приложения
    Sumki mSumki;  //фрагмент Обрабатывающий списки необходимых сумок
    SumkiItems mSumkiItems; //фрагмент для элементов списка различных сумок
    BudjetItems mBudjetItems; //фрагмент для списка элементов бюджета
    FragmentManager fm; //фрагмент менеджер
    ProgressBar mMenu_progress; //прогрес барр в панели навишации
    TextView mDays_rodov; //текст в шапки панели навигации
    TextView twUser_Name; // имя пользователя в шапке панели навигации
    babyStoradge mStor; //хранилище на диске
    FloatingActionButton fab;  //доступ к кнопке
    babyDataSet mBabyDataSet;  //экземпляр кланна для работы с данными, данные храним в памяти для сохранения пишим в базу
    babyBudjetItem mBabyItemFragment; // экземпляр фрагмента для редоатирования позиция элементов бюджета


 // Интерфейс обработки событий из фрагментов
    public  void ItemSelect (String fragmentTag, String s, int array_id)
    {
      //Toast.makeText(this, fragmentTag, Toast.LENGTH_SHORT).show();
      FragmentTransaction ft = fm.beginTransaction();
      if ( fragmentTag == mSumki.TAG ) {
          // Toast.makeText(this, "SumkiItems", Toast.LENGTH_SHORT).show();
           //mSumkiItems.setItems(getResources().getStringArray(array_id));
           mSumkiItems.setCurent_Group_ID(array_id);
           mSumkiItems.setItemList(mBabyDataSet.getSumkiItems(array_id));
           mSumkiItems.setTitle(s);
           ft.replace(R.id.main_conteyner, mSumkiItems);
           ft.addToBackStack(mSumkiItems.TAG);
      };
      if (fragmentTag == budjet.TAG) {
           //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
           //mBudjetItems.setItems(getResources().getStringArray(array_id));
           mBudjetItems.setCurent_Group_ID(array_id);
           mBudjetItems.setTitle(s);
           mBudjetItems.setItemList(mBabyDataSet.getBudjetItemList(array_id));
           ft.replace(R.id.main_conteyner, mBudjetItems);
           ft.addToBackStack( mBudjetItems.TAG );
      };
      if (fragmentTag == mSetting.TAG ) {
          readSaveData();
          setUserinfo();
      }
      if (fragmentTag == BudjetItems.TAG)
        {
            fab.setVisibility(View.INVISIBLE);
            mBabyItemFragment.setItem((babyItemBudjet) mBudjetItems.mItemList.get(array_id));
            ft.replace(R.id.main_conteyner, mBabyItemFragment);
            ft.addToBackStack( mBabyItemFragment.TAG );
        }
        if (fragmentTag == babyBudjetItem.TAG){
          babyItemBudjet m = mBabyItemFragment.getItem();
          mBabyDataSet.editBudjetItem(m);

        }


       ft.commit();
    }
//    Интерфейс обработки сообщений из диалогов
    public void babyDialogReturnItem (Object s) {
        babyFragment bf = (babyFragment) fm.findFragmentById(R.id.main_conteyner);
        dialogDataReturn ret = (dialogDataReturn) s;
        if (ret.dlgAction == R.integer.dlg_ADD ) {
            switch (bf.getTAG()) {
                case Budjet.TAG: {
                    mBabyDataSet.addBudjetList(ret.getTitle());
                   // bf.mAdapter.notifyDataSetChanged();
                    break;
                }
                case Sumki.TAG: {
                    //Toast.makeText(this.getContext(),s, Toast.LENGTH_SHORT).show();
                    mBabyDataSet.addSumkiList(ret.getTitle());
                   // bf.mAdapter.notifyDataSetChanged();
                    break;
                }
                case BudjetItems.TAG: {
                    // String str = ""+ ((dialogDataReturn)s).getTitle() + ((BudjetItems) bf).getCurent_Group_ID();
                    // Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
                    mBabyDataSet.addBudjetItem(ret.getTitle(), ((BudjetItems) bf).getCurent_Group_ID());


                   ((BudjetItems) bf).setItemList(mBabyDataSet.getBudjetItemList(((BudjetItems) bf).getCurent_Group_ID()));
                   // ((BudjetItems) bf).mAdapter.notifyDataSetChanged();

                    ((BudjetItems) bf).addItem(ret.getTitle());
                    //((BudjetItems) bf).mAdapter.notifyDataSetChanged();
                    break;
                }
                case SumkiItems.TAG: {
                    mBabyDataSet.addsumkiItems(ret.getTitle(), ((SumkiItems) bf).getCurent_Group_ID());
                    bf.addItem(((dialogDataReturn) s).getTitle());
                  //  bf.mAdapter.notifyDataSetChanged();
                    break;
                }
            }
        }
        if (ret.dlgAction == R.integer.dlg_EDIT) {
            switch (bf.getTAG()) {
                case Budjet.TAG: {
                    mBabyDataSet.editBudjetList((dialogDataReturn) s);
                  //  bf.mAdapter.notifyDataSetChanged();
                    break;
                }
                case Sumki.TAG: {
                    //Toast.makeText(this.getContext(),s, Toast.LENGTH_SHORT).show();
                    mBabyDataSet.editSumkiList((dialogDataReturn) s);
                 //   bf.mAdapter.notifyDataSetChanged();
                    break;
                }
                case BudjetItems.TAG: {
                    // String str = ""+ ((dialogDataReturn)s).getTitle() + ((BudjetItems) bf).getCurent_Group_ID();
                    // Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
                    mBabyDataSet.editBudjetItem((dialogDataReturn) s);
                    bf.editItem((dialogDataReturn) s);
                  //  bf.mAdapter.notifyDataSetChanged();
                    break;
                }
                case SumkiItems.TAG: {
                    mBabyDataSet.editSumkiItem((dialogDataReturn) s);
                    bf.editItem((dialogDataReturn) s);
                  //  bf.mAdapter.notifyDataSetChanged();
                    break;
                }
                case babyBudjetItem.TAG : {
                        bf.editItem(ret);
                }
            }
            // bf.editItem( (dialogDataReturn) s ) ;
        }
        if (ret.dlgAction==R.integer.dlg_DEL) {
            switch (bf.getTAG()) {
                case Budjet.TAG: {
                    mBabyDataSet.delBudjetList(ret.getPosition(),ret.item_ID);
                    //  bf.mAdapter.notifyDataSetChanged();
                    break;
                }
                case Sumki.TAG: {
                    //Toast.makeText(this.getContext(),s, Toast.LENGTH_SHORT).show();
                    mBabyDataSet.delSumkiList(ret.getPosition(),ret.item_ID);
                    //   bf.mAdapter.notifyDataSetChanged();
                    break;
                }
                case BudjetItems.TAG: {
                    // String str = ""+ ((dialogDataReturn)s).getTitle() + ((BudjetItems) bf).getCurent_Group_ID();
                    // Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
                    //mBabyDataSet.editBudjetItem((dialogDataReturn) s);
                    //bf.editItem((dialogDataReturn) s);
                    //  bf.mAdapter.notifyDataSetChanged();
                   mBabyDataSet.delBudjetItemsList (ret.getPosition(),ret.item_ID);
                    break;
                }
                case SumkiItems.TAG: {
                    //mBabyDataSet.editSumkiItem((dialogDataReturn) s);
                    //bf.editItem((dialogDataReturn) s);
                    //  bf.mAdapter.notifyDataSetChanged();
                    mBabyDataSet.delSumkiItemsList(ret.getPosition(),ret.item_ID);
                    break;
                }
            }
        }

     if (bf.getTAG() != babyBudjetItem.TAG)   bf.mAdapter.notifyDataSetChanged();
    }

//    читаем сохраннеые данные из хранилища телефона
    public void readSaveData (){
        mStor = new babyStoradge (getApplicationContext());
        mSetting.mStor = mStor;
        isFirst = mStor.getDataBoolean(FIRST_START);
        if (isFirst){
            // mStor.addData(FIRST_START,false);
           // CreateDataSet(); создаем при первом запуске базу SQl
           createSQLBase();

        }
        // createSQLBase();
        // заполняем поля имя пользователя и дату родов
        mUser_name = mStor.getDataString(USER_NAME);
        mDate_rodov = mStor.getDataLong(DAYS_RODOV_LONG);
        Date cur = new Date();
        days_rodov_int = (int) TimeUnit.MILLISECONDS.toDays(mDate_rodov-cur.getTime()+1);

        Toast.makeText(getApplicationContext(), "Здраствуйте,"+mUser_name+" !", Toast.LENGTH_SHORT).show();
    }

    private void CreateDataSet() {

        // создаем экземпляр класса хранилища данных в памяти
        mBabyDataSet = babyDataSet.get(getApplicationContext());
        mBabyDataSet.setBudjetList();
        mBabyDataSet.setSumkiList();
        mSumki.setItemList(mBabyDataSet.getSumkiList()); // заполняем массивы данных в классах фрагментов
        budjet.setItemList(mBabyDataSet.getBudjetList());
        mBabyDataSet.setBudjetItemsList();
        mBabyDataSet.setSumkiItems();

    }

    private void createSQLBase() {
        babyDBHelper mDB = new babyDBHelper(getApplicationContext());
        SQLiteDatabase baby_base = mDB.getWritableDatabase();
        String [] Itemlist = null;
        //Cursor c = baby_base.query("mytable", null, null, null, null, null, null);
        int[] mitemlist_budjet = {
                R.array.budjet_1, R.array.budjet_2, R.array.budjet_3, R.array.budjet_4, R.array.budjet_5, R.array.budjet_6,
                R.array.budjet_7, R.array.budjet_8, R.array.budjet_9, R.array.budjet_10, R.array.budjet_11, R.array.budjet_12,
        };
        int[] mitemlist ={
                R.array.sumki_1,
                R.array.sumki_2,
                R.array.sumki_3,
                R.array.sumki_4,
                R.array.sumki_5

        };
        Itemlist = getResources().getStringArray(R.array.budjet_items_name);
        for (int i = 0; i <Itemlist.length;i++)
        {
            ContentValues cv = new ContentValues();
            cv.put("title",Itemlist[i].toString());
            cv.put("Group_ID",1);
            long result = baby_base.insert("baby_list",null,cv);
            Log.d("DB-1", "Title = " + Itemlist[i].toString() + "ID = "+result);
            String[] pod_item = getResources().getStringArray(mitemlist_budjet[i]);

            for (int j = 0 ; j < pod_item.length; j++){
                // ID , Group_ID integer, title text, parent_ID integer, price_plan integer,price_real integer, kol_vo integer
                ContentValues cv1 = new ContentValues();
                String[] _split = pod_item[j].split(":",3);
                cv1.put("title",pod_item[j]);//_split[0].toString());
                cv1.put("parent_ID",result);
                cv1.put("Group_ID",1);
                cv1.put("price_plan",0);
                cv1.put("price_real",0);
                cv1.put("kol_vo",1);
                long result1 = baby_base.insert("baby_items",null,cv1);
                Log.d("DB-1", "Title = " + pod_item[j].toString() + "ID = "+result1);
            }
        }

        Itemlist = getResources().getStringArray(R.array.sumki);
        for (int i = 0; i <Itemlist.length;i++)
        {
            ContentValues cv = new ContentValues();
            cv.put("title",Itemlist[i].toString());
            cv.put("Group_ID",2);
            long result = baby_base.insert("baby_list",null,cv);
            Log.d("DB-1", "Title = " + Itemlist[i].toString() + "ID = "+result);
            String[] pod_item = getResources().getStringArray(mitemlist[i]);

            for (int j = 0 ; j < pod_item.length; j++){
                // ID , title text, parent_ID integer, price_plan integer,price_real integer, kol_vo integer
                ContentValues cv1 = new ContentValues();
                cv1.put("title",pod_item[j].toString());
                cv1.put("parent_ID",result);
                cv1.put("Group_ID",2);
                cv1.put("price_plan",0);
                cv1.put("price_real",0);
                cv1.put("kol_vo",1);
                long result1 = baby_base.insert("baby_items",null,cv1);
                Log.d("DB-1", "Title = " + pod_item[j].toString() + "ID = "+result1);
            }

        }

    }

    public void setUserinfo(){
        twUser_Name.setText(mUser_name);
        mDays_rodov.setText(""+days_rodov_int);
        //Date d = new Date();
        //long d_l = d.getTime();
        //d_l = days_rodov_int - d_l;

        mMenu_progress.setProgress((int)(mMenu_progress.getMax()-days_rodov_int));
    }
    protected void babyInit (){

        budjet = new Budjet();
        mSetting = new Setting();
        mSumki = new Sumki();
        mSumkiItems = new SumkiItems();
        mBudjetItems = new BudjetItems();
        mSetting = new Setting();
        mBabyItemFragment = new babyBudjetItem();
        fm = getSupportFragmentManager();
        readSaveData();
        CreateDataSet();

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab ) {
            babyFragment bf = (babyFragment) fm.findFragmentById(R.id.main_conteyner);
            bf.dialogAdd();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = (View) navigationView.getHeaderView(0);
        twUser_Name = (TextView) header.findViewById(R.id.user_name);
        mMenu_progress = (ProgressBar) header.findViewById(R.id.menu_progress);
        mDays_rodov = (TextView) header.findViewById(R.id.days_do_rodov);
        babyInit();
        //setUserinfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
       // readSaveData();
        setUserinfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.setVisibility(View.VISIBLE);
        setUserinfo();
        //mDays_rodov.setText(USER_NAME.toCharArray(),0,2);
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_conteyner, budjet);
        ft.commit();
        if ( isFirst ) {
            fab.setVisibility(View.INVISIBLE);
            ft = fm.beginTransaction();
            ft.replace(R.id.main_conteyner, mSetting);
            ft.addToBackStack( mSetting.TAG );
            ft.commit();
        };

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_add) {
            babyFragment bf = (babyFragment) fm.findFragmentById(R.id.main_conteyner);
           bf.dialogAdd();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // mDays_rodov.setText(USER_NAME);
        fab.setVisibility(View.VISIBLE);
        FragmentTransaction ft = fm.beginTransaction();

        if (id == R.id.nav_budjet) {
          if (fm.findFragmentByTag( budjet.TAG) == null ) {
              // Handle the camera action
              ft.replace(R.id.main_conteyner, budjet);
              ft.addToBackStack( budjet.TAG );
          }
        } else if (id == R.id.nav_bugs) {
            if (fm.findFragmentByTag(mSumki.TAG) == null ) {

                ft.replace(R.id.main_conteyner, mSumki);
                ft.addToBackStack(mSumki.TAG);
            }
        } else if (id == R.id.nav_setting) {
            fab.setVisibility(View.INVISIBLE);
            ft.replace(R.id.main_conteyner, mSetting);
            ft.addToBackStack( mSetting.TAG );
        }
        ft.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
