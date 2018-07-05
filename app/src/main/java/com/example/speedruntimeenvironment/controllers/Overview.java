package com.example.speedruntimeenvironment.controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import com.example.speedruntimeenvironment.daos.api.GamesDAO;
import com.example.speedruntimeenvironment.daos.impl.GamesDAOImpl;
import com.example.speedruntimeenvironment.model.Category;
import com.example.speedruntimeenvironment.model.Game;
import com.example.speedruntimeenvironment.model.Leaderboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Overview extends Fragment {

    private SpeedrunRestUsage client;

    private String GameID;

    private TextView name, year, devices;

    private ImageView imageView;

    private AtomicReference<Game> mGame = new AtomicReference<>();

    private Game currentGame;

    private GamesDAO gamesDAO;

    List<Game> FGameList;

    private static final String TAG = "Sub_MainActivity";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_overview, container, false);

        gamesDAO = new GamesDAOImpl();
        try {
            FGameList = this.gamesDAO.getFavoriteGamesFromFile(getString(R.string.favorites), getActivity());
        } catch (IOException e) {
            Log.e(TAG, "initGames: File not found", e);
        }

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

                currentGame = game;
                for(Game g: FGameList){
                    if(g.getId().equalsIgnoreCase(currentGame.getId())){
                        ((Sub_MainActivity)getActivity()).getFavorize().setVisible(false);
                        ((Sub_MainActivity)getActivity()).getUnfavorize().setVisible(true);
                    }
                }

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
                intent.putExtra("game", mGame.get());
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

    public void addFavorite(){
        try {
            FGameList = this.gamesDAO.getFavoriteGamesFromFile(getString(R.string.favorites), getActivity());
        } catch (IOException e) {
            Log.e(TAG, "initGames: File not found", e);
        }
        FGameList.add(currentGame);
        try {
            this.gamesDAO.favoriteGamesToFile(getString(R.string.favorites), getActivity(), FGameList);
        } catch (IOException e) {
            Log.e(TAG, "initGames: File not found", e);
        }
    }

    public void removeFavorite(){
        try {
            FGameList = this.gamesDAO.getFavoriteGamesFromFile(getString(R.string.favorites), getActivity());
        } catch (IOException e) {
            Log.e(TAG, "initGames: File not found", e);
        }
        List<Game> save = new ArrayList<>();
        for(Game g: FGameList){
            if(!g.getId().equalsIgnoreCase(currentGame.getId())){
                    save.add(g);
                //Toast.makeText(getActivity(),"Löschen", Toast.LENGTH_SHORT).show();
            }
        }
        try {
            this.gamesDAO.favoriteGamesToFile(getString(R.string.favorites), getActivity(), save);
        } catch (IOException e) {
            Log.e(TAG, "initGames: File not found", e);
        }
    }

}
