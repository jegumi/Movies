package com.jegumi.movies.events;

import com.jegumi.movies.model.Movie;

public class OpenMovieEvent {
    public Movie movie;

    public OpenMovieEvent(Movie movie) {
        this.movie = movie;
    }
}
