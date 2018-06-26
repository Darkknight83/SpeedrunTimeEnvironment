package com.example.speedruntimeenvironment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsunderebug.speedrun4j.game.Game;
import com.tsunderebug.speedrun4j.game.GameList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Overview extends Fragment {

    private List<Game> games;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_overview, container, false);

        TextView test = v.findViewById(R.id.tw_ov);


        return v;
    }

}
