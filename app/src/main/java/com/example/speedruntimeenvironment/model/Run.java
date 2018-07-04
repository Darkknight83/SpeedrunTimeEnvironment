package com.example.speedruntimeenvironment.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Run implements Serializable{
    private String runId;

    private int place;

    private String vodUri;

    private String playerName;

    private long timeInSek;

    public static Run fromJson(JSONObject runJson, JSONObject playerJson) throws JSONException{
        Run run = new Run();

        JSONObject runJsonObj = runJson.getJSONObject("run");

        run.setRunId(runJsonObj.getString("id"));
        run.setPlace(runJson.getInt("place"));


        if(runJsonObj.isNull("videos")) {
            run.setVodUri("http://www.youtube.com/");
        } else {
            JSONObject videosJson = runJsonObj.getJSONObject("videos");


            JSONArray linksJson = videosJson.getJSONArray("links");

            if(linksJson != null && linksJson.length() > 0) {
                run.setVodUri(linksJson.getJSONObject(0).getString("uri"));
            }
        }


        JSONObject times = runJsonObj.getJSONObject("times");

        run.setTimeInSek(times.getLong("primary_t"));

        if(playerJson.isNull("names")) {
            run.setPlayerName("Unknown");
        } else {
            JSONObject names = playerJson.getJSONObject("names");
            if (names.getString("international") == null) {
                run.setPlayerName(names.getString("japanese"));
            } else {
                run.setPlayerName(names.getString("international"));
            }
        }




        return run;

    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getVodUri() {
        return vodUri;
    }

    public void setVodUri(String vodUri) {
        this.vodUri = vodUri;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public long getTimeInSek() {
        return timeInSek;
    }

    public void setTimeInSek(long timeInSek) {
        this.timeInSek = timeInSek;
    }

    @Override
    public String toString() {
        return "Run{" +
                "runId='" + runId + '\'' +
                ", place=" + place +
                ", vodUri='" + vodUri + '\'' +
                ", playerName='" + playerName + '\'' +
                ", timeInSek=" + timeInSek +
                '}';
    }
}
