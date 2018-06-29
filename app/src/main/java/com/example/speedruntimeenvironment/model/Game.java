package com.example.speedruntimeenvironment.model;

public class Game {
    private String id;

    private String name;

    private String abbreviation;

    private String weblink;

    public Game() {

    }


    public Game(String id, String name, String abbreviation, String weblink) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.weblink = weblink;
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
}
