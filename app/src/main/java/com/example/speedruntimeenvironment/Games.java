package com.example.speedruntimeenvironment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tsunderebug.speedrun4j.game.Game;
import com.tsunderebug.speedrun4j.game.GameList;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Games extends Fragment{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Game> games;
    public static final String GAMES = "GAMES";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_games, container, false);

//--------------RecyclerView mit Layout verbinden


        mRecyclerView = (RecyclerView) v.findViewById(R.id.games_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

//--------------Liste
        games = new ArrayList<>();
        fetchPopular();
//--------------Liste dem Adapter übergeben

        mRecyclerView.setHasFixedSize(true);
        mAdapter = new GamesAdapter(games, new GamesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Game item) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.overview));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    public void fetchPopular()
    {
        Thread t = new Thread(new Runnable()
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
        });
        t.start();
    }



    //Ein bestimmtes Spiel suchen und hinzufügen
    public void fetchSearch(final String name)
    {
        Thread t = new Thread(new Runnable()
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
        });
        t.start();

    }
    //get für Games liste
    public List<Game> getGames()
    {
        return games;
    }

    public void saveList(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(games);
        editor.putString(GAMES, json);
        editor.apply();
    }

    public void loadList(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(GAMES, null);
        Type type = new TypeToken<ArrayList<Game>>() {}.getType();
        games = gson.fromJson(json, type);

        if(games == null){
            games = new ArrayList<Game>();
        }
    }
}

