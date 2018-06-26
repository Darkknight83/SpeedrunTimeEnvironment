package com.example.speedruntimeenvironment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Streams extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> GList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_streams, container, false);


//--------------RecyclerView mit Layout verbinden

        mRecyclerView = (RecyclerView) v.findViewById(R.id.streams_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

//--------------DummyListe

        GList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            GList.add("Test World!");
            GList.add("Ja");
        }


//--------------Liste dem Adapter übergeben



        return v;
    }
}
