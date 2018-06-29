package com.example.speedruntimeenvironment.daos.impl;

import android.util.Log;

import com.example.speedruntimeenvironment.daos.api.GamesDAO;

import java.util.ArrayList;
import java.util.List;

public class GamesDAOImpl implements GamesDAO {


    @Override
    public List<String> getAllGamesAsStrings() {

        // from JSON-File TODO

        List<String> gameNames = new ArrayList<>();
        for(int i = 0; i < 15; i++) {
            gameNames.add("Hello from DAO: " + i);
        }
        return gameNames;
    }

}
