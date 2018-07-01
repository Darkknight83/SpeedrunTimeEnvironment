package com.example.speedruntimeenvironment.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.speedruntimeenvironment.R;

public class Leaderboard extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_leaderboard, container, false);

        TextView standard = v.findViewById(R.id.defaultleaderboard);
        standard.setText("Leaderboard");

        return v;
    }
}