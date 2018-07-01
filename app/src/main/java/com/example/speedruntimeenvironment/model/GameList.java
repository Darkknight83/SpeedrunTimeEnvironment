package com.example.speedruntimeenvironment.model;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameList {

    List<Game> games;


    public GameList() {
        games = new ArrayList<>();
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public static GameList fromJson(JSONObject jsonObject) throws JSONException{
        GameList gameList = new GameList();

        JSONArray data = jsonObject.getJSONArray("data");
        for(int i = 0; i < data.length(); i++) {
            Game game = new Game();

            game.setId(jsonObject.getJSONArray("data").getJSONObject(i).getString("id"));
            game.setName(jsonObject.getJSONArray("data").getJSONObject(i).getJSONObject("names").getString("international"));
            gameList.addGame(game);
        }

        return gameList;
    }

    // todo: unteren 3 methoden refactorn zu einer?

    public List<String> getGameNamesAsStrings() {
        List<String> retVal = new ArrayList<>();
        for(Game g : games) {
            retVal.add(g.getName());
        }
        return retVal;
    }

    public List<String> getIdsAsStrings() {
        List<String> retVal = new ArrayList<>();
        for(Game g : games) {
            retVal.add(g.getId());
        }
        return retVal;
    }

    public List<String> getImageUrlAsStrings() {
        List<String> retVal = new ArrayList<>();
        for(Game g : games) {
            retVal.add(g.getUrlImage());
        }
        return retVal;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
