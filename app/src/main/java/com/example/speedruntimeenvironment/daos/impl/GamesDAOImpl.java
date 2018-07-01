package com.example.speedruntimeenvironment.daos.impl;

import android.content.Context;
import android.util.Log;

import com.example.speedruntimeenvironment.daos.api.GamesDAO;
import com.example.speedruntimeenvironment.model.Game;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GamesDAOImpl implements GamesDAO {
    private static final String TAG = "GamesDAOImpl";

    @Override
    public List<String> getAllGamesAsStrings() {
        Log.d(TAG, "getAllGamesAsStrings: START");
        // from JSON-File TODO

        List<String> gameNames = new ArrayList<>();
        for(int i = 0; i < 15; i++) {
            gameNames.add("Hello from DAO: " + i);
        }

        Log.d(TAG, "getAllGamesAsStrings: END; DS -> " + gameNames.size());

        return gameNames;
    }

    // unsauber, dass context hier ins dao reingegeben wird?
    @Override
    public List<Game> getPopularGamesFromFile(String games_file, Context context) throws IOException, JSONException {
        Log.d(TAG, "getGamesFromFile: START");

        List<Game> games = new ArrayList<>();


        //

        String json = null;

        InputStream is = context.getAssets().open(games_file);

        byte[] buffer = new byte[is.available()];

        is.read(buffer);

        is.close();

        json = new String(buffer, "UTF-8");


        // JSONParser parser = new JSONParser();
        // JSONArray data = (JSONArray) parser.parse(new FileReader(games_file));
        JSONArray data = new JSONArray(json);


        Game singleGame = null;
        for(int i = 0; i < data.length(); i++) {
            singleGame = new Game();

            // get id from Json and set it to Game-object
            try {
                JSONObject gameJson = (JSONObject) data.get(i);
                singleGame.setId((String)gameJson.get("id"));
                singleGame.setName((String)gameJson.get("name"));
                singleGame.setUrlImage((String)gameJson.get("cover-small"));

                games.add(singleGame);
            } catch (JSONException e) {
                Log.e(TAG, "getGamesFromFile: Cannot convert from JSON-file ", e);
                continue;
            }
        }

        Log.d(TAG, "getGamesFromFile: END; DS -> " + games.size());

        return games;

    }

}
