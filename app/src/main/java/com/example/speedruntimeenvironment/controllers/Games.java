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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Games extends Fragment {

    private static final String TAG = "Games";

    private List<String> mGameNames = new ArrayList<>();
    private List<String> mGameImageUrls = new ArrayList<>();


    // ui
    private RecyclerView recyclerView;
    private GamesRecyclerAdapter adapter;



    // private HttpClientSpeedrun httpClient;
    private GamesDAO gamesDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_games, container, false);

        Log.d(TAG, "onCreateView: started");

        // init
        this.gamesDAO = new GamesDAOImpl();


        initImageBitmaps(v);

        initImageBitmaps(v);

        return v;


//--------------RecyclerView mit Layout verbinden

        /*
        this.mRecyclerView = (RecyclerView) v.findViewById(R.id.games_list);
        this.mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(this.mLayoutManager);


        mRecyclerView.setHasFixedSize(true);
        // mAdapter = new GamesRecyclerAdapter(httpClient.getGamesAsArray());
        mAdapter = new GamesRecyclerAdapter(this.mGames);
        mRecyclerView.setAdapter(mAdapter);

//--------------DummyListe


        // gList = this.httpClient.getGamesAsStrings();

        // this.gList = this.gamesDAO.getAllGamesAsStrings();

        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();

        params.put("_bulk", "yes");
        params.put("max" , "1000");

        client.get(constructUrl("/games/"), params,  new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(TAG, "getAllGamesBulk() successful; JSON: " + response.toString());

                try {
                    GameList gamelist = GameList.fromJson(response);
                    mGames = gamelist.getGames();
                    Log.d(TAG, "onSuccess() completed");

                } catch (JSONException e){
                    e.printStackTrace();
                    Log.d(TAG, "Error while parsing JSON");
                    mGames = null;
                }
                mAdapter.setGames(mGames);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "Error while fetching data");
            }
        });
        */
    }

    private void initImageBitmaps(View v) {

        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        // getting imageUrls + adding them to list

        mGameImageUrls.add("https://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/img_01.jpg");
        mGameNames.add("Spatz");

        mGameImageUrls.add("https://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/img_02.jpg");
        mGameNames.add("Kirche");

        mGameImageUrls.add("https://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/img_03.jpg");
        mGameNames.add("Paris");

        /*
        AsyncHttpClient client = new AsyncHttpClient();


        RequestParams params = new RequestParams();

        params.put("_bulk", "yes");
        params.put("max" , "1000");

        client.get(constructUrl("/games/"), params,  new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(TAG, "getAllGamesBulk() successful; JSON: " + response.toString());

                try {
                    GameList gamelist = GameList.fromJson(response);
                    mGameNames = gamelist.getGamesAsStrings();
                    Log.d(TAG, "onSuccess() completed");

                } catch (JSONException e){
                    e.printStackTrace();
                    Log.d(TAG, "Error while parsing JSON");
                    mGameNames = null;
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "Error while fetching data from Speedrun.com");
            }
        });
        */

        initRecyclerView(v);

    }

    private void initRecyclerView(View v) {
        Log.d(TAG, "initRecyclerView: START");

        recyclerView = (RecyclerView) v.findViewById(R.id.games_list);
        adapter = new GamesRecyclerAdapter(getActivity(), mGameNames, mGameImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        // updateList();

        makeApiCall();
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

    private void makeApiCall() {
        Log.d(TAG, "makeApiCall: START");
        
        SpeedrunRestUsage client = new SpeedrunRestUsage();

        client.getGamesBulk(this.adapter);
    }




}
