package com.example.speedruntimeenvironment.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.speedruntimeenvironment.R;
import com.example.speedruntimeenvironment.controllers.adapters.GamesRecyclerAdapter;
import com.example.speedruntimeenvironment.controllers.speedrun.http.SpeedrunRestUsage;
import com.example.speedruntimeenvironment.daos.api.GamesDAO;
import com.example.speedruntimeenvironment.daos.impl.GamesDAOImpl;
import com.example.speedruntimeenvironment.model.Game;
import com.example.speedruntimeenvironment.model.GameList;

import org.json.JSONException;

import java.io.IOException;




public class Games extends Fragment {

    private static final String TAG = "Games";

    private final String GAMES_FILE = "popular_games.json";   //

    // neu: eine zentrale Liste
    private GameList mGameList = new GameList();    // muss nicht init werden hier TODO: check

    // ui
    private RecyclerView recyclerView;
    private GamesRecyclerAdapter adapter;
    View v;


    // http-requests
    SpeedrunRestUsage client;


    // dao fuer Persistierung spaeter
    private GamesDAO gamesDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: started");

        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.activity_games, container, false);

        // init
        this.gamesDAO = new GamesDAOImpl();
        this.client = new SpeedrunRestUsage();


        initGames(v, false);
        
        return v;
    }

    private boolean initGames(View v, boolean listFav) {

        Log.d(TAG, "initGames: START");

        // hole games und adde sie zur liste
        if (listFav) {
            try {
                mGameList.setGames(this.gamesDAO.getFavoriteGamesFromFile(getString(R.string.favorites), this.getActivity()));
            } catch (IOException e){
                Log.e(TAG, "initGames: File not found", e);
            }
        } else {
            try {
                mGameList.setGames(this.gamesDAO.getPopularGamesFromFile(GAMES_FILE, this.getActivity()));
        } catch(IOException e){
            Log.e(TAG, "initGames: File not found", e);    // TODO: Toast hier: "Couldn't load games"
        } catch(JSONException e){
            Log.e(TAG, "initGames: Error while parsing File", e);  //TODO: Toast hier: "Couldn't load games"
        }
    }
        if (mGameList.isEmpty()){
            Toast.makeText(this.getActivity(),"No Favorites", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            initRecyclerView(v);
            return true;
        }
    }

    private void initRecyclerView(View v) {
        Log.d(TAG, "initRecyclerView: START");

        recyclerView = (RecyclerView) v.findViewById(R.id.games_list);
        // adapter = new GamesRecyclerAdapter(getActivity(), mGameNames, mGameImageUrls, mGameIds);
        adapter = new GamesRecyclerAdapter(getActivity(), this.mGameList.getGameNamesAsStrings(), this.mGameList.getImageUrlAsStrings(), this.mGameList.getIdsAsStrings(), new GamesRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String GameID) {
                getActivity().finish();
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.overview));
                intent.putExtra("GameID", GameID);
                startActivity(intent);
            }
        });


        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

    }

    public boolean updateRecycler(boolean listFav){
        return initGames(v, listFav);
    }
}