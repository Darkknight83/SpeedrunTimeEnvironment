package com.example.speedruntimeenvironment.controllers;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.speedruntimeenvironment.R;
import com.example.speedruntimeenvironment.controllers.alerts.DeleteDialog;
import com.example.speedruntimeenvironment.daos.api.GamesDAO;
import com.example.speedruntimeenvironment.daos.impl.GamesDAOImpl;
import com.example.speedruntimeenvironment.model.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Favorites extends Fragment {

    private List<Game> FGameList;
    private GamesDAO gamesDAO;
    private static final String TAG = "Favorites";
    FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_favorites, container, false);

        FragmentManager manager = getFragmentManager();

        gamesDAO = new GamesDAOImpl();
        Button delfav = (Button) v.findViewById(R.id.deletefav);
        delfav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               DeleteDialog del =  new DeleteDialog();
               del.show(manager, TAG);
            }
        });
        return v;
    }
}
