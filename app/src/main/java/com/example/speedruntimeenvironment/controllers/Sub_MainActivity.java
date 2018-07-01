package com.example.speedruntimeenvironment.controllers;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.speedruntimeenvironment.R;

public class Sub_MainActivity extends AppCompatActivity {

//--------------Attribute

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private MenuItem favorize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_navigation);
        setTitle(getIntent().getStringExtra("Destination"));

//--------------Toolbar als Actionbar setzen

        Toolbar toolbar = findViewById(R.id.sub_toolbarmenu);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

//--------------Fragmente werden anhand des Titels, welcher durch ein Intent übergeben wird ausgewählt

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        if (getTitle().equals(getString(R.string.favorites)))
            fragmentTransaction.replace(R.id.sub_content_frame, new Favorites());
        if (getTitle().equals(getString(R.string.notifications)))
            fragmentTransaction.replace(R.id.sub_content_frame, new Notifications());
        if (getTitle().equals(getString(R.string.backup)))
            fragmentTransaction.replace(R.id.sub_content_frame, new BackUp());
        if (getTitle().equals(getString(R.string.faq)))
            fragmentTransaction.replace(R.id.sub_content_frame, new Faq());
        if (getTitle().equals(getString(R.string.appinfo)))
            fragmentTransaction.replace(R.id.sub_content_frame, new AppInfo());
        if (getTitle().equals(getString(R.string.overview)))
            fragmentTransaction.replace(R.id.sub_content_frame, new Overview());
        if (getTitle().equals(getString(R.string.streampage)))
            fragmentTransaction.replace(R.id.sub_content_frame, new Stream());
        if (getTitle().equals(getString(R.string.leaderboard)))
            fragmentTransaction.replace(R.id.sub_content_frame, new Leaderboard());
        if (getTitle().equals(getString(R.string.guides)))
            fragmentTransaction.replace(R.id.sub_content_frame, new Guides());

        fragmentTransaction.commit();
    }

//--------------Items der Toolbar hinzufügen

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub_toolbarmenu, menu);


        //Sichtbarkeitssteuerung der Items
        favorize = menu.findItem(R.id.action_favorite);

        if (getTitle().equals(getString(R.string.overview))) {
            favorize.setVisible(true);
        } else {
            favorize.setVisible(false);
        }

        return true;
    }

//--------------Aktionen beim drücken der Actionbar Items hier einfügen

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
            //to-do
            //case R.id.action_favorite:

        }
        return super.onOptionsItemSelected(item);
    }

}
