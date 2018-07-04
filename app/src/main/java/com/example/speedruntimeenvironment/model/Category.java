package com.example.speedruntimeenvironment.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Category {

    private String categoryId;

    private String name;

    private Leaderboard leaderboard;


    public static Category fromJson(JSONObject obj) throws JSONException {
        Category cat = new Category();


        cat.setCategoryId(obj.getString("id"));
        cat.setName(obj.getString("name"));


        return cat;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }
}
