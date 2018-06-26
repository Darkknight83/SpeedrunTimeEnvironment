package com.example.speedruntimeenvironment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Games extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> GList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_games, container, false);

//--------------RecyclerView mit Layout verbinden

        mRecyclerView = (RecyclerView) v.findViewById(R.id.games_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

//--------------DummyListe

        GList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            GList.add("Hello World!");
            }

//--------------Liste dem Adapter übergeben

        mRecyclerView.setHasFixedSize(true);
        mAdapter = new StringAdapter(GList);
        mRecyclerView.setAdapter(mAdapter);


        return v;
    }

}
