package com.jegumi.movies.model;

import java.io.Serializable;

public class Movies implements Serializable {
    private Movie[] movies;

    public Movie[] getMovies() {
        return movies;
    }

    public void setMovies(Movie[] movies) {
        this.movies = movies;
    }
}
