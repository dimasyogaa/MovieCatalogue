package com.yogadimas.moviecatalogue.data.source.remote.responses.movie.discover;

import com.google.gson.annotations.SerializedName;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;

import java.util.ArrayList;

public class MoviesResponse {

    @SerializedName("results")
    protected ArrayList<MovieEntity> results;

    public ArrayList<MovieEntity> getResults() {
        return results;
    }

}