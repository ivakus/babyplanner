package ru.c0ner.babyplaner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import ru.c0ner.babyplaner.Fragments.Budjet;
import ru.c0ner.babyplaner.Fragments.BudjetItems;
import ru.c0ner.babyplaner.Fragments.Setting;
import ru.c0ner.babyplaner.Fragments.Sumki;
import ru.c0ner.babyplaner.Fragments.SumkiItems;
import ru.c0ner.babyplaner.Fragments.babyFragment;

public class app_main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , babyFragment.onItemSelectListiner{


    Budjet budjet;
    Setting mSetting;
    Sumki mSumki;
    SumkiItems mSumkiItems;
    BudjetItems mBudjetItems;
    FragmentManager fm;

    public  void ItemSelect (String fragmentTag, String s, int array_id)
    {
      Toast.makeText(this, fragmentTag, Toast.LENGTH_SHORT).show();
        FragmentTransaction ft = fm.beginTransaction();
       if ( fragmentTag == mSumki.TAG ) {
           Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
           mSumkiItems.setItems(getResources().getStringArray(array_id));
           mSumkiItems.setTitle(s);
           ft.replace(R.id.main_conteyner, mSumkiItems);
           ft.addToBackStack(mSumkiItems.TAG);
       };
       if (fragmentTag == budjet.TAG) {
           Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
           mBudjetItems.setItems(getResources().getStringArray(array_id));
           mBudjetItems.setTitle(s);
           ft.replace(R.id.main_conteyner, mBudjetItems);
           ft.addToBackStack( mBudjetItems.TAG );
       };

        ft.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        budjet = new Budjet();
        mSetting = new Setting();
        mSumki = new Sumki();
        mSumkiItems = new SumkiItems();
        mBudjetItems = new BudjetItems();
        fm = getSupportFragmentManager();


    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_conteyner, budjet);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

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

        }
        ft.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
