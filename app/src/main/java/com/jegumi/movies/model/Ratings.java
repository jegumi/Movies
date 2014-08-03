package com.jegumi.movies.model;

import java.io.Serializable;

public class Ratings implements Serializable {
    private String critics_rating;
    private int critics_score;
    private String audiende_rating;
    private int audience_score;

    public String getCritics_rating() {
        return critics_rating;
    }

    public void setCritics_rating(String critics_rating) {
        this.critics_rating = critics_rating;
    }

    public int getCritics_score() {
        return critics_score;
    }

    public void setCritics_score(int critics_score) {
        this.critics_score = critics_score;
    }

    public String getAudiende_rating() {
        return audiende_rating;
    }

    public void setAudiende_rating(String audiende_rating) {
        this.audiende_rating = audiende_rating;
    }

    public int getAudience_score() {
        return audience_score;
    }

    public void setAudience_score(int audience_score) {
        this.audience_score = audience_score;
    }
}
