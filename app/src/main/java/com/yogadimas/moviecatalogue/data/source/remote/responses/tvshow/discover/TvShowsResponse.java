package com.yogadimas.moviecatalogue.data.source.remote.responses.tvshow.discover;

import com.google.gson.annotations.SerializedName;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;

import java.util.ArrayList;

public class TvShowsResponse {

    @SerializedName("results")
    protected ArrayList<TvShowEntity> results;

    public ArrayList<TvShowEntity> getResults() {
        return results;
    }

}