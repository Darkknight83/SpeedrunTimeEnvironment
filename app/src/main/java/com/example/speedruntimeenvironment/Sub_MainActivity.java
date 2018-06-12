package com.example.speedruntimeenvironment;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Sub_MainActivity extends AppCompatActivity {

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

//--------------Frame in dem die Fragments erscheinen

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

//--------------Auswahl des Fragments

        if(getTitle().equals(getString(R.string.favorites))) fragmentTransaction.replace(R.id.sub_content_frame, new Favorites());
        if(getTitle().equals(getString(R.string.notifications))) fragmentTransaction.replace(R.id.sub_content_frame, new Notifications());
        if(getTitle().equals(getString(R.string.backup))) fragmentTransaction.replace(R.id.sub_content_frame, new BackUp());
        if(getTitle().equals(getString(R.string.faq))) fragmentTransaction.replace(R.id.sub_content_frame, new Faq());
        if(getTitle().equals(getString(R.string.appinfo))) fragmentTransaction.replace(R.id.sub_content_frame, new AppInfo());

        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub_toolbarmenu, menu);

        favorize = menu.findItem(R.id.action_favorite);

        if(getTitle().equals(getString(R.string.overview))){
            favorize.setVisible(true);
        } else {
            favorize.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
               onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}