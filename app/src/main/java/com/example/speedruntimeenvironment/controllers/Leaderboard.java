package com.example.speedruntimeenvironment.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.speedruntimeenvironment.R;
import com.example.speedruntimeenvironment.controllers.adapters.LeaderRecyclerAdapter;
import com.example.speedruntimeenvironment.model.GameList;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard extends Fragment {

    private RecyclerView recyclerView;
    private LeaderRecyclerAdapter adapter;
    private List<String> categoies;

   // private LeaderList mLeaderList = new LeaderList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_leaderboard, container, false);

//----------Sollte auch hier die GameID liefern, auf dessen Basis die Leaderboards geholt werden
        Intent intent = getActivity().getIntent();
        String gameId = intent.getStringExtra("GameID");

 //----------TO-DO Soll die verschiedenen Kategorien enthalten
        categoies = new ArrayList<>();

//----------Dummy-Content
        categoies.add("Any%");
        categoies.add("100%");
        categoies.add("0 Exit");
        categoies.add("99 Exit");

//----------Hier wird die Tabbar erstellt
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);

//----------Hier werden die Kategorien in das Tablayout geladen
        for (String a: categoies) {
            tabLayout.addTab(tabLayout.newTab().setText(a));
        }
//---------------- Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//---------- Listener für die Tabauswahl
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
              //  updateRecycler(getListe(tab.getText())); anhand des Tabnames die richtige Liste für die Kategorie erstellen/wählen
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

/*
        updateRecycler(getListe(tab.getText()));

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity())); */

        return v;
    }
//-------- Idee: Recycler mit passender Liste füllen. Prinzip wie bei der Liste in Games
   /* public void updateRecycler(List<Leader> mLeaderList){
        adapter = new LeaderRecyclerAdapter(getActivity(), this.mLeaderList.getRanksAsStrings(), this.mLeaderList.getPlayersAsStrings(), this.mLeaderList.getTimeAsStrings(), this.mLeaderList.getPlatformAsStrings(), this.mLeaderList.getDateAsStrings(), new LeaderRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String RankID) {

            }
        });

        recyclerView.setAdapter(adapter);
    }*/

}