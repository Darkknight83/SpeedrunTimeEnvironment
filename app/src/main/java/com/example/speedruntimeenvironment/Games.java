package com.example.speedruntimeenvironment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tsunderebug.speedrun4j.game.Game;
import com.tsunderebug.speedrun4j.game.GameList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Games extends Fragment {

    List<Game> games;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_games, container, false);
        games = new ArrayList<>();
        
        // Inflate the layout for this fragment
        return view;
    }

    //Spiele der der Definierten Liste zu Games hinzufügen
    public void fetchPopular()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String[] s_games = {"Super Mario","Metroid","Celest"};
                for (String s: s_games)
                {
                    try
                    {
                        games.addAll(Arrays.asList(GameList.withName(s).getGames()));
                    }
                    catch(IOException e)
                    {
                        System.out.print(e);
                    }
                }
            }
        }).start();

    }

    //Ein bestimmtes Spiel suchen und hinzufügen
    public void fetchSearch(String name)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    games.addAll(Arrays.asList(GameList.withName(name).getGames()));
                }
                catch(IOException e)
                {
                    System.out.print(e);
                }
            }
        }).start();


}

    //get für Games liste
    public List<Game> getGames()
    {
        return games;
    }

    //Games liste in ein Array umwandeln mit namen der Spiele als string
    public String[] arrayToString()
    {
        List<String> tmp = new ArrayList<>();
        for(Game g : games)
        {
            tmp.add(g.toString());
        }
        return tmp.toArray(new String[tmp.size()]);
    }

}
