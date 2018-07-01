package com.example.speedruntimeenvironment.controllers;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.speedruntimeenvironment.R;

public class Overview extends Fragment {

    private String GameID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_options, container, false);

        Button leaderboard = (Button) v.findViewById(R.id.btn_leaderboard);
        leaderboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.leaderboard));
                startActivity(intent);
            }
        });

        Button guides = (Button) v.findViewById(R.id.btn_guides);
        guides.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.guides));
                startActivity(intent);
            }
        });

        Button streams = (Button) v.findViewById(R.id.btn_streams);
        streams.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.streampage));
                intent.putExtra("GameID", getGameID());
                startActivity(intent);
            }
        });

        return v;
    }

    public String getGameID(){
        return GameID;
    }
}
