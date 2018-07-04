package com.example.speedruntimeenvironment.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leaderboard {
    private List<Run> runs;


    public Leaderboard() {
        runs = new ArrayList<>();
    }



    public static Leaderboard fromJson(JSONObject response) throws JSONException{

        Leaderboard leaderboard = new Leaderboard();

        JSONObject data = response.getJSONObject("data");

        JSONArray runsJson = data.getJSONArray("runs");

        List<Run> runs = new ArrayList<>();

        for(int i = 0; i < runsJson.length() || i < 10; i++) {
                runs.add(Run.fromJson(runsJson.getJSONObject(i)));
        }

        leaderboard.setRuns(runs);

        return leaderboard;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
    }
}
