package com.yogadimas.moviecatalogue.data.source.remote.responses.tvshow.detail;

import com.google.gson.annotations.SerializedName;

public class DetailTvShowResponse {
    @SerializedName("first_air_date")
    protected final String firstAirDate;
    @SerializedName("poster_path")
    protected final String posterPath;
    @SerializedName("id")
    private final int id;
    @SerializedName("name")
    private final String name;
    @SerializedName("vote_average")
    private final double voteAverage;
    @SerializedName("overview")
    private final String overview;

    public DetailTvShowResponse(int id, String name, double voteAverage, String overview, String firstAirDate, String posterPath) {
        this.id = id;
        this.name = name;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.firstAirDate = firstAirDate;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getName() {
        return name;
    }


}