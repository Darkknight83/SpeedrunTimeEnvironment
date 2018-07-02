package com.example.speedruntimeenvironment.controllers.speedrun.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.speedruntimeenvironment.controllers.adapters.GamesRecyclerAdapter;
import com.example.speedruntimeenvironment.controllers.callbacks.GameInfoCallback;
import com.example.speedruntimeenvironment.model.Game;
import com.example.speedruntimeenvironment.model.GameList;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import cz.msebera.android.httpclient.Header;


public class SpeedrunRestUsage {
    private static final String TAG = "SpeedrunRestUsage";


    /*
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
    */

    /*
    public Game getGameInfos(String gameId, final TextView name, final TextView year, final TextView devices, final ImageView img) {
        Log.d(TAG, "getGameInfos: START");

        RequestParams params = new RequestParams();

        params.put("embed", "platforms");

        String url = "/games/" + gameId;

        // final AtomicReference<Game> retVal = new AtomicReference<>();


        SpeedrunRestClient.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Game game = Game.fromJson(response);

                    // retVal.set(game);

                    name.setText(game.getName());

                    year.setText(String.valueOf(game.getReleaseYear()));

                    // generiere String für platforms
                    StringBuilder platforms = new StringBuilder();
                    List<String> platList = game.getPlatforms();
                    for(String p : platList) {
                        platforms.append(p);
                        platforms.append(", ");       // TODO: Leo: String richtig generieren, dass komma richtg sind
                    }

                    // get image from url
                    try {

                        URL url = new URL(game.getUrlImage());
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream input = connection.getInputStream();
                        Bitmap myBitmap = BitmapFactory.decodeStream(input);

                        img.setImageBitmap(myBitmap);

                        Log.d(TAG, "onSuccess: Image set");
                    } catch (IOException e) {
                        Log.e(TAG, "onSuccess: Image konnte nicht ausgelesen werden", e);
                    }


                    devices.setText(platforms.toString());
                    // imageview: bild holen und setzen
                } catch (JSONException e) {
                    Log.e(TAG, "onSuccess: Fehler beim Konvertieren der JSON", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "onFailure: JSON konnte fuer Game nicht geholt werden", throwable); // TODO ggf toast
            }
        });

        Game game = new Game();

        game.setUrlImage("https://imgsv.imaging.nikon.com/lineup/lens/zoom/normalzoom/af-s_dx_18-140mmf_35-56g_ed_vr/img/sample/img_04.jpg");
        game.setPlatforms(Collections.<String>emptyList());
        game.setReleaseYear(1998);
        game.setId("12345");

        // return retVal.get();
        return game;
    }
    */


    public void getGameInfos(String gameId, GameInfoCallback gameInfoCallback) {
        Log.d(TAG, "getGameInfos: START");

        RequestParams params = new RequestParams();

        params.put("embed", "platforms");

        String url = "/games/" + gameId;

        SpeedrunRestClient.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Log.d(TAG, "onSuccess: START");
                    Game game = Game.fromJson(response);

                    
                    gameInfoCallback.onSuccess(game);
                    
                } catch (JSONException e) {
                    Log.e(TAG, "onSuccess: Fehler beim Konvertieren der JSON", e);
                    gameInfoCallback.onFail();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "onFailure: JSON konnte fuer Game nicht geholt werden", throwable); // TODO ggf toast
                gameInfoCallback.onFail();
            }
        });
    }
}
