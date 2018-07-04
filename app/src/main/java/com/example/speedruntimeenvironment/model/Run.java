package com.example.speedruntimeenvironment.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Run {
    private String runId;

    private int place;

    private String vodUri;

    public static Run fromJson(JSONObject obj) throws JSONException{
        Run run = new Run();

        JSONObject runJson = obj.getJSONObject("run");

        run.setRunId(runJson.getString("id"));
        run.setPlace(obj.getInt("place"));

        JSONObject videosJson = runJson.getJSONObject("videos");
        JSONArray linksJson = videosJson.getJSONArray("links");
        if(linksJson != null && linksJson.length() > 0) {
            run.setVodUri(linksJson.getJSONObject(0).getString("uri"));
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
}
