package com.example.speedruntimeenvironment;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean doubleTap = false;

    private MenuItem search;
    private MenuItem filter;

    private DrawerLayout mDrawerLayout;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        setTitle(getString(R.string.games));


//--------------Toolbar als Actionbar setzen und Burgermenu hinzufügen

        Toolbar toolbar = findViewById(R.id.toolbarmenu);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

//--------------Frame in dem die Fragments erscheinen. Als Startfragment "Games" gesetzt

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, new Games());
        fragmentTransaction.commit();

//--------------NavigationDrawer (SwipeMenu + Listener)

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//--------------set item as selected to persist highlight
                        menuItem.setChecked(true);
//--------------close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

//--------------Fragment wechselt, wenn Seite ausgewählt auf Basis der ItemId, Titel neu gesetzt

                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();

                        Fragment page;

                        switch(menuItem.getItemId()){
                            case R.id.nav_games:
                                page = new Games();
                                setTitle(getString(R.string.games));
                                break;

                            case R.id.nav_streams:
                                page = new Streams();
                                setTitle(getString(R.string.streams));
                                break;

                            case R.id.nav_options:
                                page = new Options();
                                setTitle(getString(R.string.options));
                                break;

                                default:
                                    page = new Games();
                                    setTitle(getString(R.string.games));
                                    break;
                        }

                        fragmentTransaction.replace(R.id.content_frame, page);
                        fragmentTransaction.commit();

//--------------Die Toolbar wird dem Fragment angepasst

                        if(getTitle().equals(getString(R.string.games))){
                            search.setVisible(true);
                            filter.setVisible(true);
                        }
                        if(getTitle().equals(getString(R.string.streams))){
                            search.setVisible(true);
                            filter.setVisible(true);
                        }
                        if(getTitle().equals(getString(R.string.options))){
                            search.setVisible(false);
                            filter.setVisible(false);
                        }

                        return true;
                    }
                });
    }

//--------------Items der Toolbar hinzufügen, Verfügbar machen zum Verbergen/Anzeigen

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tooblarmenu, menu);

        search = menu.findItem(R.id.action_search);
        filter = menu.findItem(R.id.action_filter);

        return true;
    }

//--------------Aktionen beim drücken der Actionbar Items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_search:

        }
        return super.onOptionsItemSelected(item);
    }

//--------------Damit die Back-Taste das Menü schließt statt aus der App zu gehen

    @Override
    public void onBackPressed() {

            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawers();
            } else {
                if (doubleTap) {
                    super.onBackPressed();
                } else {
                    Toast.makeText(this, "Double tab to exit", Toast.LENGTH_SHORT).show();
                    doubleTap = true;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleTap = false;
                        }
                    }, 1000);
                }
            }
        }
}