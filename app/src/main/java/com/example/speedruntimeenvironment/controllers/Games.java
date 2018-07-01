package com.example.speedruntimeenvironment.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.speedruntimeenvironment.R;
import com.example.speedruntimeenvironment.controllers.adapters.GamesRecyclerAdapter;
import com.example.speedruntimeenvironment.controllers.speedrun.http.SpeedrunRestUsage;
import com.example.speedruntimeenvironment.daos.api.GamesDAO;
import com.example.speedruntimeenvironment.daos.impl.GamesDAOImpl;
import com.example.speedruntimeenvironment.model.GameList;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Games extends Fragment {

    private static final String TAG = "Games";

    private final String GAMES_FILE = "popular_games.json";   // TODO

    // listen fuer recyclerview
    private List<String> mGameNames = new ArrayList<>();
    private List<String> mGameImageUrls = new ArrayList<>();
    private List<String> mGameIds = new ArrayList<>();

    // neu: eine zentrale Liste
    private GameList mGameList = new GameList();    // muss nicht init werden hier TODO: check

    // ui
    private RecyclerView recyclerView;
    private GamesRecyclerAdapter adapter;


    // http-requests
    SpeedrunRestUsage client;


    // dao fuer Persistierung spaeter
    private GamesDAO gamesDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: started");

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_games, container, false);

        // init
        this.gamesDAO = new GamesDAOImpl();
        this.client = new SpeedrunRestUsage();


        initGames(v);

        return v;
    }

    private void initGames(View v) {

        Log.d(TAG, "initGames: START");

        // hole games und adde sie zur liste

        try {
            mGameList.setGames(this.gamesDAO.getPopularGamesFromFile(GAMES_FILE, this.getActivity()));
        } catch (IOException e) {
            Log.e(TAG, "initGames: JSON-file not found", e);    // TODO: Toast hier: "Couldn't load games"
        } catch (JSONException e) {
            Log.e(TAG, "initGames: Error while parsing JSON-file", e);  //TODO: Toast hier: "Couldn't load games"
        }


        /*
        mGameImageUrls.add("https://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/img_01.jpg");
        mGameNames.add("Spatz");

        mGameImageUrls.add("https://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/img_02.jpg");
        mGameNames.add("Kirche");

        mGameImageUrls.add("https://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/img_03.jpg");
        mGameNames.add("Paris");
        */

        initRecyclerView(v);

    }

    private void initRecyclerView(View v) {
        Log.d(TAG, "initRecyclerView: START");

        recyclerView = (RecyclerView) v.findViewById(R.id.games_list);
        // adapter = new GamesRecyclerAdapter(getActivity(), mGameNames, mGameImageUrls, mGameIds);
        adapter = new GamesRecyclerAdapter(getActivity(), this.mGameList.getGameNamesAsStrings(), this.mGameList.getImageUrlAsStrings());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        // updateList();

        // erstelleGamesListe();



    }

    private void updateList() {
        Log.d(TAG, "updateList: START");

        List<String> neueImageUrls = new ArrayList<>();
        List<String> neueImageNames = new ArrayList<>();

        neueImageUrls.add("https://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/img_04.jpg");
        neueImageNames.add("Blumenfeld");

        neueImageUrls.add("https://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/img_05.jpg");
        neueImageNames.add("Statue");

        // mNames = neueImageNames;
        // mImageUrls = neueImageUrls;

        adapter.setImageNames(neueImageNames);
        adapter.setImages(neueImageUrls);

        adapter.notifyDataSetChanged();
    }

    private void erstelleGamesListe() {
        Log.d(TAG, "erstelleGamesListe: START");

        client.getGamesBulk(this.adapter);


    }




}
