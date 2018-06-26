package com.example.speedruntimeenvironment.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.speedruntimeenvironment.R;
import com.example.speedruntimeenvironment.daos.api.GamesDAO;
import com.example.speedruntimeenvironment.daos.impl.GamesDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class Games extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> gList;

    private GamesDAO gamesDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_games, container, false);

        // init dao
        gamesDAO = new GamesDAOImpl();



//--------------RecyclerView mit Layout verbinden

        mRecyclerView = (RecyclerView) v.findViewById(R.id.games_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


//--------------DummyListe



        gList = this.gamesDAO.getAllGamesAsStrings();

//--------------Liste dem Adapter übergeben

        mRecyclerView.setHasFixedSize(true);
        mAdapter = new StringAdapter(gList);
        mRecyclerView.setAdapter(mAdapter);


        return v;
    }

}
