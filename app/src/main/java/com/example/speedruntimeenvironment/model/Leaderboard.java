package com.example.speedruntimeenvironment.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leaderboard implements Serializable{
    private List<Run> runs;

    private String platform;


    public Leaderboard() {
        runs = new ArrayList<>();
    }



    public static Leaderboard fromJson(JSONObject response) throws JSONException{

        Leaderboard leaderboard = new Leaderboard();

        JSONObject data = response.getJSONObject("data");

        JSONArray runsJson = data.getJSONArray("runs");

        JSONArray playerJson = data.getJSONObject("players").getJSONArray("data");

        List<Run> runs = new ArrayList<>();

        for(int i = 0; i < runsJson.length() || i < 10; i++) {
                runs.add(Run.fromJson(runsJson.getJSONObject(i), playerJson.getJSONObject(i)));
        }



        JSONObject platforms = data.getJSONObject("platforms");

        JSONArray dataPlatforms = platforms.getJSONArray("data");

        JSONObject singlePlatform = dataPlatforms.getJSONObject(0);

        leaderboard.setPlatform(singlePlatform.getString("name"));

        leaderboard.setRuns(runs);

        return leaderboard;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "runs=" + runs +
                '}';
    }

    public List<String> getRanksAsStrings() {
        List<String> retVal = new ArrayList<>();
        for(Run r : this.runs) {
            retVal.add(String.valueOf(r.getPlace()));
        }
        return retVal;
    }

    public List<String> getPlayerNamesAsStrings() {
        List<String> retVal = new ArrayList<>();
        for(Run r : this.runs) {
            retVal.add(r.getPlayerName());
        }
        return retVal;
    }

    public List<String> getRunIdsAsStrings() {
        List<String> retVal = new ArrayList<>();
        for(Run r : this.runs) {
            retVal.add(r.getRunId());
        }
        return retVal;
    }

    public List<String> getVodUrlAsStrings() {
        List<String> retVal = new ArrayList<>();
        for(Run r : this.runs) {
            retVal.add(r.getVodUri());
        }
        return retVal;
    }

    public List<String> getTimeAsStrings() {
        List<String> retVal = new ArrayList<>();
        for(Run r : this.runs) {
            retVal.add(String.valueOf(r.getTimeInSek()));
        }
        return retVal;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
