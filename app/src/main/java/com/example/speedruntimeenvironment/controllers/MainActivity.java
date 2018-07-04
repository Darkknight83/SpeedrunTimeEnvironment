package com.example.speedruntimeenvironment.controllers;


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

import com.example.speedruntimeenvironment.R;

public class MainActivity extends AppCompatActivity {

//--------------Attribute

    private boolean doubleTap = false;

    //Toolbar Items, die sichtbar gemacht oder versteckt werden können
    private MenuItem search;
    private MenuItem favs;
    private MenuItem all;

    private DrawerLayout mDrawerLayout;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;



//--------------Methode die beim Erstellen der Activity aufgerufen wird

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

//--------------Listener für den Navigation Drawer

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true); //Highlight Games beim Start
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//--------------set item as selected to persist highlight
                        menuItem.setChecked(true);
//--------------close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

//--------------Fragment wechselt, wenn Seite ausgewählt auf Basis der ItemId, Titel neu gesetzt, Toolbar Items Sichtbarkeit angepasst

                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();

                        Fragment page;

                        switch (menuItem.getItemId()) {
                            case R.id.nav_games:
                                page = new Games();
                                setTitle(getString(R.string.games));
                                search.setVisible(true);
                                favs.setVisible(true);
                                all.setVisible(false);
                                break;

                            case R.id.nav_streams:
                                page = new Streams();
                                setTitle(getString(R.string.streams));
                                search.setVisible(true);
                                favs.setVisible(true);
                                all.setVisible(false);
                                break;

                            case R.id.nav_options:
                                page = new Options();
                                setTitle(getString(R.string.options));
                                search.setVisible(false);
                                favs.setVisible(false);
                                all.setVisible(false);
                                break;

                            default:
                                page = new Games();
                                setTitle(getString(R.string.games));
                                search.setVisible(true);
                                favs.setVisible(true);
                                all.setVisible(false);
                                break;
                        }

                        fragmentTransaction.replace(R.id.content_frame, page);
                        fragmentTransaction.commit();

                        return true;
                    }
                });
    }

//--------------Items der Toolbar hinzufügen

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tooblarmenu, menu);

        //um die Items ansteuern zu können
        search = menu.findItem(R.id.action_search);
        favs = menu.findItem(R.id.action_favs);
        all = menu.findItem(R.id.action_all);
        all.setVisible(false);

        return true;
    }

    //--------------Aktionen beim drücken der Actionbar Items hier einfügen
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_search:
                return true;

            case R.id.action_favs:
                favs.setVisible(false);
                all.setVisible(true);
                return true;
            case R.id.action_all:
                favs.setVisible(true);
                all.setVisible(false);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

//--------------Damit die Back-Taste das Menü schließt statt aus der App zu gehen + Doubletab Funktion

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
