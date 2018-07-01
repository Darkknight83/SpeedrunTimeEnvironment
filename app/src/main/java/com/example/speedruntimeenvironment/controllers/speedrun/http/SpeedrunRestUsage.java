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
import java.util.concurrent.atomic.AtomicReference;

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
                    List<String> neueImageUrls = new ArrayList<>();     // TODO: aendern, sodass List<Game> in Games verwaltet wird
                    List<String> neueIds = gamelist.getIdsAsStrings();
                    // List<String> games = gamelist.getGamesAsStrings();

                    neueImageUrls.add("https://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/img_04.jpg");
                    neueImageUrls.add("https://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/img_05.jpg");



                    //adapter.setImageNames(gamelist.getGamesAsStrings());
                    adapter.setImages(neueImageUrls);
                    //adapter.setIds(gamelist.getIdsAsStrings());

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


    public String getImageUrlToGame(String pGameId) {
        Log.d(TAG, "getImageUrlToGame: START");

        String url = "/games/" + pGameId;

        final AtomicReference<String> retVal = new AtomicReference<>();

        SpeedrunRestClient.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // super.onSuccess(statusCode, headers, response); //
                Log.d(TAG, "onSuccess: successful http-call - Code " + statusCode);
                try {
                    Game game = Game.fromJson(response);
                    retVal.set(game.getUrlImage());

                } catch (JSONException e){
                    e.printStackTrace();
                    retVal.set("");
                    Log.d(TAG, "Error while parsing JSON");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);    // TODO: custom exception werfen und im controller behandeln
            }
        });

        return retVal.get();

    }

}
