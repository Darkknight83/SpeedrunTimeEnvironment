package com.example.speedruntimeenvironment.controllers;

import android.content.Intent;
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
import com.example.speedruntimeenvironment.controllers.callbacks.GameInfoCallback;
import com.example.speedruntimeenvironment.controllers.speedrun.http.SpeedrunRestUsage;
import com.example.speedruntimeenvironment.model.Game;

import java.util.List;

public class Overview extends Fragment {

    private SpeedrunRestUsage client;

    private String GameID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_overview, container, false);

        client = new SpeedrunRestUsage();

        Intent intent = getActivity().getIntent();

        TextView name = v.findViewById(R.id.game_name);
        TextView year = v.findViewById(R.id.game_year);
        TextView devices = v.findViewById(R.id.game_devices);
        ImageView img = v.findViewById(R.id.game_img);

        //Hier dann die Objekt-Informationen passend einfügen

        String gameId = intent.getStringExtra("GameID");

        // Game gameAlt = client.getGameInfos(gameId, name, year, devices, img);

        client.getGameInfos(gameId, new GameInfoCallback() {

            @Override
            public void onSuccess(Game game) {
                // update UI
                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();

                StringBuilder platforms = new StringBuilder();
                List<String> platList = game.getPlatforms();
                for(String p : platList) {
                    platforms.append(p);
                    platforms.append(", ");       // TODO: Leo: String richtig generieren, dass komma richtg sind
                }


                name.setText(game.getName());
                year.setText(String.valueOf(game.getReleaseYear()));
                devices.setText(platforms.toString());


            }

            @Override
            public void onFail() {
                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
            }
        });



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
                startActivity(intent);
            }
        });

        Button guides = (Button) v.findViewById(R.id.btn_guides);
        guides.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.guides));
                startActivity(intent);
            }
        });

        Button streams = (Button) v.findViewById(R.id.btn_streams);
        streams.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sub_MainActivity.class);
                intent.putExtra("Destination", getString(R.string.streampage));
                intent.putExtra("GameID", getGameID());
                startActivity(intent);
            }
        });

        return v;
    }

    public String getGameID(){
        return GameID;
    }
}
