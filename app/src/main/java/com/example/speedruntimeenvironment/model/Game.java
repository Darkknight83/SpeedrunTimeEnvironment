package com.example.speedruntimeenvironment.model;


import com.example.speedruntimeenvironment.controllers.Leaderboard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable{
    private String id;

    private String name;

    private String abbreviation;

    private String weblink;

    private String urlImage;

    private int releaseYear;

    private List<String> platforms;

    private List<Category> categories;

    public Game() {

    }


    public Game(String id, String name, String abbreviation, String weblink) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.weblink = weblink;
    }

    public static Game fromJson(JSONObject root) throws JSONException{
        Game game = new Game();

        JSONObject data = root.getJSONObject("data");

        game.setId(data.getString("id"));

        game.setReleaseYear(data.getInt("released"));

        game.setName(data.getJSONObject("names").getString("international"));

        game.setAbbreviation(data.getString("abbreviation"));

        List<String> platforms = new ArrayList<>();
        JSONArray platData = data.getJSONObject("platforms").getJSONArray("data");

        for(int i = 0; i < platData.length(); i++)
        {
            JSONObject plat = (JSONObject)platData.get(i);
            platforms.add(plat.getString("name"));
        }

        game.setPlatforms(platforms);

        game.setUrlImage(data.getJSONObject("assets").getJSONObject("cover-medium").getString("uri"));

        return game;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public String getWeblink() {
        return this.weblink;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String getUrlImage() {
        return this.urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
