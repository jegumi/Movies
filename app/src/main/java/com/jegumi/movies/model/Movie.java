package com.jegumi.movies.model;

import java.io.Serializable;

public class Movie implements Serializable {

    private String id;
    private String title;
    private String year;
    private String mpaa_rating;
    private String runtime;
    private String critics_consensus;
    private Release_dates release_dates;
    private Ratings ratings;
    private String synopsis;
    private Poster posters;
    private Cast[] abridged_cast;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMpaa_rating() {
        return mpaa_rating;
    }

    public void setMpaa_rating(String mpaa_rating) {
        this.mpaa_rating = mpaa_rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getCritics_consensus() {
        return critics_consensus;
    }

    public void setCritics_consensus(String critics_consensus) {
        this.critics_consensus = critics_consensus;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Poster getPosters() {
        return posters;
    }

    public void setPosters(Poster posters) {
        this.posters = posters;
    }

    public Release_dates getRelease_dates() {
        return release_dates;
    }

    public void setRelease_dates(Release_dates release_dates) {
        this.release_dates = release_dates;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public Cast[] getAbridged_cast() {
        return abridged_cast;
    }

    public void setAbridged_cast(Cast[] abridged_cast) {
        this.abridged_cast = abridged_cast;
    }
}
