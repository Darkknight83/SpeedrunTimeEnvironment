package com.example.speedruntimeenvironment.daos.api;

import android.content.Context;

import com.example.speedruntimeenvironment.model.Game;
import com.example.speedruntimeenvironment.model.GameList;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface GamesDAO {

    List<String> getAllGamesAsStrings();

    List<Game> getPopularGamesFromFile(String games_file, Context context) throws IOException, JSONException;

    List<Game> getFavoriteGamesFromFile(String games_file,Context context) throws IOException;

    void favoriteGamesToFile(String games_file, Context context,List<Game> favorites) throws IOException;
}
