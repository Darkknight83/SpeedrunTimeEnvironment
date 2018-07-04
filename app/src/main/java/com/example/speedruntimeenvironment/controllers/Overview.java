package com.example.speedruntimeenvironment.controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.speedruntimeenvironment.R;
import com.example.speedruntimeenvironment.controllers.callbacks.GameCategoryCallback;
import com.example.speedruntimeenvironment.controllers.callbacks.GameImageCallback;
import com.example.speedruntimeenvironment.controllers.callbacks.GameInfoCallback;
import com.example.speedruntimeenvironment.controllers.callbacks.LeaderboardCallback;
import com.example.speedruntimeenvironment.controllers.speedrun.http.SpeedrunRestUsage;
import com.example.speedruntimeenvironment.model.Category;
import com.example.speedruntimeenvironment.model.Game;
import com.example.speedruntimeenvironment.model.Leaderboard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Overview extends Fragment {

    private SpeedrunRestUsage client;

    private String GameID;

    private TextView name, year, devices;

    private ImageView imageView;

    private AtomicReference<Game> mGame = new AtomicReference<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_overview, container, false);

        client = new SpeedrunRestUsage();

        Intent intent = getActivity().getIntent();

        final TextView name = v.findViewById(R.id.game_name);
        final TextView year = v.findViewById(R.id.game_year);
        final TextView devices = v.findViewById(R.id.game_devices);
        imageView = v.findViewById(R.id.game_img);

        //Hier dann die Objekt-Informationen passend einfügen

        final String gameId = intent.getStringExtra("GameID");

        final List<Category> categories = new ArrayList<>();


        client.getGameInfos(gameId, new GameInfoCallback() {

            @Override
            public void onSuccess(Game game) {

                // update UI
                StringBuilder platforms = new StringBuilder();
                List<String> platList = game.getPlatforms();
                for(String p : platList) {
                    platforms.append(p);
                    platforms.append(", ");
                }

                platforms.setCharAt(platforms.lastIndexOf(","), ' ');

                name.setText(game.getName());
                year.setText(String.valueOf(game.getReleaseYear()));
                devices.setText(platforms.toString());


                mGame.set(game);


                // hole image über url und setze imageview
                client.getUrl(mGame.get().getUrlImage(), new GameImageCallback() {
                    @Override
                    public void gameImgLoaded(Bitmap img) {
                        imageView.setImageBitmap(img);
                    }
                });

                // hole categories über Schnittstelle
                client.getCategoriesToGame(mGame.get().getId(), new GameCategoryCallback() {
                    @Override
                    public void onGetCategoriesSuccess(List<Category> pCategories) {
                        mGame.get().setCategories(pCategories);
                        categories.addAll(pCategories);
                    }
                });


            }

            @Override
            public void onFail() {
                Toast.makeText(getActivity(), "Couldn't receive Game Info", Toast.LENGTH_SHORT).show();
            }
        });



        // hole Leaderboards zu verschiedenen Categories über Schnittstelle
        // TODO: wirft in 105 nullpointer
        /*
        if(mGame.get().getCategories() != null) {
            for(final Category cat : mGame.get().getCategories()) {
                client.getLeaderboardsToCategories(mGame.get().getId(), cat.getCategoryId(), new LeaderboardCallback(){
                    @Override
                    public void onLeaderboardReceived(Leaderboard leaderboard) {
                        cat.setLeaderboard(leaderboard);
                    }
                });
            }
        }
        */




        // name.setText(intent.getStringExtra("GameID"));
        name.setTextColor(Color.BLACK);
        // year.setText( String.valueOf(game.getReleaseYear()));



        // devices.setText("");
        // Img.setImage...

        Button leaderboard = (Button) v.findViewById(R.id.btn_leaderboard);
        leaderboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.leaderboard));
                intent.putExtra("GameID", gameId);
                startActivity(intent);
            }
        });

        Button guides = (Button) v.findViewById(R.id.btn_guides);
        guides.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.guides));
                intent.putExtra("GameID", gameId);
                startActivity(intent);
            }
        });

        Button streams = (Button) v.findViewById(R.id.btn_streams);
        streams.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.streampage));
                intent.putExtra("GameID", gameId);
                startActivity(intent);
            }
        });

        return v;
    }

    public String getGameID(){
        return GameID;
    }
}
