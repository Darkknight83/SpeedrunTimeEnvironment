package com.example.speedruntimeenvironment.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.speedruntimeenvironment.R;
import com.example.speedruntimeenvironment.controllers.adapters.LeaderRecyclerAdapter;
import com.example.speedruntimeenvironment.controllers.callbacks.LeaderboardCallback;
import com.example.speedruntimeenvironment.controllers.speedrun.http.SpeedrunRestUsage;
import com.example.speedruntimeenvironment.controllers.speedrun.http.utils.UTIL;
import com.example.speedruntimeenvironment.model.Category;
import com.example.speedruntimeenvironment.model.Game;
import com.example.speedruntimeenvironment.model.GameList;
import com.example.speedruntimeenvironment.model.Run;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Leaderboard extends Fragment {

    private static final String TAG = "Leaderboard";

    private SpeedrunRestUsage client;

    private RecyclerView recyclerView;
    private LeaderRecyclerAdapter adapter;
    private List<String> categories;

    private Game mGame;

    private AtomicReference<com.example.speedruntimeenvironment.model.Leaderboard> mLeaderboard = new AtomicReference<>();


   // private LeaderList mLeaderList = new LeaderList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_leaderboard, container, false);

//----------Sollte auch hier die GameID liefern, auf dessen Basis die Leaderboards geholt werden
        Intent intent = getActivity().getIntent();
        String gameId = intent.getStringExtra("GameID");

        this.client = new SpeedrunRestUsage();




        mGame = (Game) intent.getSerializableExtra("game");

        client.getLeaderboardsToCategories(mGame.getId(), mGame.getCategories().get(0).getCategoryId(), new LeaderboardCallback() {
            @Override
            public void onLeaderboardReceived(com.example.speedruntimeenvironment.model.Leaderboard leaderboard) {
                Log.d(TAG, "onLeaderboardReceived: " + leaderboard);
                mLeaderboard.set(leaderboard);
                updateRecycler(mLeaderboard.get());

            }
        });

 //----------TO-DO Soll die verschiedenen Kategorien enthalten
        categories = new ArrayList<>();

        List<Category> cats = mGame.getCategories();
        for(Category c : cats) {
            categories.add(c.getName());
        }

//----------Hier wird die Tabbar erstellt
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);


//----------Hier werden die Kategorien in das Tablayout geladen
        for (String a: categories) {
            if(tabLayout.getTabCount()<5) {
                tabLayout.addTab(tabLayout.newTab().setText(a));
            }
        }
//---------------- Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//---------- Listener für die Tabauswahl
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                client.getLeaderboardsToCategories(mGame.getId(), mGame.getCategories().get(tab.getPosition()).getCategoryId(), new LeaderboardCallback() {
                    @Override
                    public void onLeaderboardReceived(com.example.speedruntimeenvironment.model.Leaderboard leaderboard) {
                        Log.d(TAG, "onLeaderboardReceived: " + leaderboard);
                        mLeaderboard.set(leaderboard);
                        updateRecycler(mLeaderboard.get());
                        adapter.notifyDataSetChanged();
                    }
                });
                // updateRecycler(getListe(tab.getText()), tab.getPosition()); anhand des Tabnames die richtige Liste für die Kategorie erstellen/wählen
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
// called when tab unselected
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
// called when a tab is reselected
            }
        });

        recyclerView = (RecyclerView) v.findViewById(R.id.leader_list);

//--------hier sollte dann der erste Tab den Recycler initialisieren


        // updateRecycler(getListe(tab.getText()));

        // recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return v;
    }

    //-------- Idee: Recycler mit passender Liste füllen. Prinzip wie bei der Liste in Games


    public void updateRecycler(com.example.speedruntimeenvironment.model.Leaderboard leaderboard){
        List<String> platforms = new ArrayList<>();
        for(Run r : leaderboard.getRuns()) {
            platforms.add(leaderboard.getPlatform());
        }

        // transformiere Sekunden in lesbares Format

        List<String> times = leaderboard.getTimeAsStrings();
        List<String> formattedTimes = new ArrayList<>();

        for(String t : times) {

            StringBuilder formatted = new StringBuilder();
            int[] hms = UTIL.erzeugeTimeFormat(Long.parseLong(t));


            formatted.append(hms[0]);
            formatted.append(":");
            formatted.append(hms[1]);
            formatted.append(":");
            formatted.append(hms[2]);

            formattedTimes.add(formatted.toString());
        }

        adapter = new LeaderRecyclerAdapter(getActivity(),  leaderboard.getRanksAsStrings(), leaderboard.getPlayerNamesAsStrings(), formattedTimes, platforms, new LeaderRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String RankID) {

            }
        });

        recyclerView.setAdapter(adapter);

    }




}