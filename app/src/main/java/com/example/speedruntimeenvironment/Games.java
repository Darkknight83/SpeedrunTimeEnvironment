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
import java.util.Arrays;
import java.util.List;

public class Games extends Fragment {

    Game[] games;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_games, container, false);
        try
        {
            fetchPopular();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ListView listview = (ListView)view.findViewById(R.id.mainList);

        /*ArrayAdapter<Game> listViewAdapter = new ArrayAdapter<Game>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                games
        );*/

        //listview.setAdapter(listViewAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    private void fetchPopular() throws IOException
    {
        String[] s_games = {"Super Mario","Metroid","Celest"};
        /*for (String s: s_games)
        {
            games.addAll(Arrays.asList(GameList.withName(s).getGames()));
        }*/
        games = GameList.withName("Super Mario").getGames();
    }

}
