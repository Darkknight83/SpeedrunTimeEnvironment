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
import com.example.speedruntimeenvironment.controllers.callbacks.GameCategoryCallback;
import com.example.speedruntimeenvironment.controllers.callbacks.GameImageCallback;
import com.example.speedruntimeenvironment.controllers.callbacks.GameInfoCallback;
import com.example.speedruntimeenvironment.controllers.callbacks.LeaderboardCallback;
import com.example.speedruntimeenvironment.model.Category;
import com.example.speedruntimeenvironment.model.CategoryList;
import com.example.speedruntimeenvironment.model.Game;
import com.example.speedruntimeenvironment.model.GameList;
import com.example.speedruntimeenvironment.model.Leaderboard;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
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

    public void getGameInfos(String gameId, final GameInfoCallback gameInfoCallback) {
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

    public void getUrl(String url, final GameImageCallback callback)
    {
        Log.d(TAG, "getUrl: START");

        SpeedrunRestClient.getAbsolute(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Bitmap image = BitmapFactory.decodeByteArray(responseBody, 0, responseBody.length);

                callback.gameImgLoaded(image);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, "onFailure: START", error);
            }
        });
    }

    public void getCategoriesToGame(String gameId, final GameCategoryCallback gameCategoryCallback) {
        Log.i(TAG, "getCategoriesToGame: START");

        // build relative url
        StringBuilder sb = new StringBuilder();
        sb.append("/games/");
        sb.append(gameId);
        sb.append("/categories");

        SpeedrunRestClient.get(sb.toString(), null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                CategoryList categoryList = null;
                try {
                    categoryList = CategoryList.fromJson(response);
                } catch (JSONException e) {
                    Log.e(TAG, "onSuccess: Error while Parsing Json response", e);
                }
                gameCategoryCallback.onGetCategoriesSuccess(categoryList.getCategories());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "onFailure: Error while fetching data from Speedrun.com", throwable);
            }
        });
    }
    public void getLeaderboardsToCategories(String gameId, String catId, final LeaderboardCallback leaderboardCallback) {
        // build url
        StringBuilder sb = new StringBuilder();
        sb.append("/leaderboards/");
        sb.append(gameId);
        sb.append("/category/");
        sb.append(catId);
        sb.append("?top=10&embed=players,platforms");

        SpeedrunRestClient.get(sb.toString(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Leaderboard leaderboard = null;
                try {
                    leaderboard = Leaderboard.fromJson(response);
                } catch (JSONException e) {
                    Log.e(TAG, "onSuccess: Error while parsing JSON", e);
                }

                leaderboardCallback.onLeaderboardReceived(leaderboard);
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e(TAG, "onFailure: Unsuccessful request", throwable);
                leaderboardCallback.onFailure();
            }
        });
    }
}
