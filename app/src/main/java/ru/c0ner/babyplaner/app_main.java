package ru.c0ner.babyplaner;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import ru.c0ner.babyplaner.Fragments.Budjet;
import ru.c0ner.babyplaner.Fragments.BudjetItems;
import ru.c0ner.babyplaner.Fragments.Setting;
import ru.c0ner.babyplaner.Fragments.Sumki;
import ru.c0ner.babyplaner.Fragments.SumkiItems;
import ru.c0ner.babyplaner.Fragments.babyAddDialog;
import ru.c0ner.babyplaner.Fragments.babyFragment;
import ru.c0ner.babyplaner.Fragments.babyStoradge;

public class app_main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , babyFragment.onItemSelectListiner, FloatingActionButton.OnClickListener, babyAddDialog.babyDialogreturnListener {

    final String STORADGE_NAME = "baby_v1";
    final String USER_NAME = "USER_NAME";
    final String FIRST_START = "FIRST_START";
    boolean isFirst = true;
    final String DAYS_RODOV = "DAYS_RODOV";

    int days_rodov_int = 180;
    String mUser_name = "Гость";
    Budjet budjet;
    Setting mSetting;
    Sumki mSumki;
    SumkiItems mSumkiItems;
    BudjetItems mBudjetItems;
    FragmentManager fm;
    SharedPreferences mSaveData;
    ProgressBar mMenu_progress;
    TextView mDays_rodov;
    babyStoradge mStor;
    FloatingActionButton fab;



    public  void ItemSelect (String fragmentTag, String s, int array_id)
    {
      //Toast.makeText(this, fragmentTag, Toast.LENGTH_SHORT).show();
      FragmentTransaction ft = fm.beginTransaction();
      if ( fragmentTag == mSumki.TAG ) {
           //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
           mSumkiItems.setItems(getResources().getStringArray(array_id));
           mSumkiItems.setTitle(s);
           ft.replace(R.id.main_conteyner, mSumkiItems);
           ft.addToBackStack(mSumkiItems.TAG);
      };
      if (fragmentTag == budjet.TAG) {
           //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
           mBudjetItems.setItems(getResources().getStringArray(array_id));
           mBudjetItems.setTitle(s);
           ft.replace(R.id.main_conteyner, mBudjetItems);
           ft.addToBackStack( mBudjetItems.TAG );
      };

       ft.commit();
    }
    public void babyDialogReturnItem (String s) {
        babyFragment bf = (babyFragment) fm.findFragmentById(R.id.main_conteyner);
        bf.addItem(s);
    }
    protected void readSaveData (){
        mStor = new babyStoradge (getApplicationContext());
        mSetting.mStor = mStor;
        isFirst = mStor.getDataBoolean(FIRST_START);
        if (isFirst){
            // mStor.addData(FIRST_START,false);

        }
        mUser_name = mStor.getDataString(USER_NAME);
        days_rodov_int = mStor.getDataInt(DAYS_RODOV);
        Toast.makeText(getApplicationContext(), mUser_name, Toast.LENGTH_SHORT).show();
    }
    protected void babyInit (){


        budjet = new Budjet();
        mSetting = new Setting();
        mSumki = new Sumki();
        mSumkiItems = new SumkiItems();
        mBudjetItems = new BudjetItems();
        mSetting = new Setting();
        fm = getSupportFragmentManager();
        mDays_rodov = findViewById(R.id.days_do_rodov);
        mMenu_progress = findViewById(R.id.menu_progress);
        readSaveData();

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
     //   TextView user = (TextView) navigationView.findViewById(R.id.user_name);
     //   user.setText(mUser_name);
        babyInit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.setVisibility(View.VISIBLE);
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
