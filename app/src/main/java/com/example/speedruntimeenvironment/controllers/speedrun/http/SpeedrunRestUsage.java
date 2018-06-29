package com.example.speedruntimeenvironment.controllers.speedrun.http;

import android.util.Log;
import android.widget.Adapter;

import com.example.speedruntimeenvironment.controllers.adapters.GamesRecyclerAdapter;
import com.example.speedruntimeenvironment.model.Game;
import com.example.speedruntimeenvironment.model.GameList;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SpeedrunRestUsage {
    private static final String TAG = "SpeedrunRestUsage";

    public void getGamesBulk(GamesRecyclerAdapter adapter) {

        Log.d(TAG, "getGamesBulk: START");

        RequestParams params = new RequestParams();

        params.put("_bulk", "yes");
        params.put("max" , "1000");

        SpeedrunRestClient.get("/games", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // super.onSuccess(statusCode, headers, response); //
                Log.d(TAG, "onSuccess: successful http-call - Code: " + statusCode);

                try {
                    GameList gamelist = GameList.fromJson(response);

                    // placeholder
                    List<String> neueImageUrls = new ArrayList<>();
                    List<Game> games = gamelist.getGames();
                    for(Game g : games) {
                        neueImageUrls.add(g.getId()); // todo: imageUrls herausfinden und in liste packen
                    }

                    adapter.setImageNames(gamelist.getGamesAsStrings());
                    adapter.setImages(neueImageUrls);

                } catch (JSONException e){
                    e.printStackTrace();
                    Log.d(TAG, "Error while parsing JSON");         // todo: hier eventuell neue Exception werfen und im Controller behandeln
                    adapter.setImageNames(Collections.emptyList());
                    adapter.setImages(Collections.emptyList());
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);    // TODO: custom exception werfen und im controller behandeln
            }
        });
    }

}
