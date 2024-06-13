package com.yogadimas.moviecatalogue.data.source.remote.responses.movie.detail;

import com.google.gson.annotations.SerializedName;

public class DetailMovieResponse {

    @SerializedName("release_date")
    protected final String releaseDate;
    @SerializedName("poster_path")
    protected final String posterPath;
    @SerializedName("id")
    private final int id;
    @SerializedName("title")
    private final String title;
    @SerializedName("vote_average")
    private final double voteAverage;
    @SerializedName("overview")
    private final String overview;

    public DetailMovieResponse(int id, String title, double voteAverage, String overview, String releaseDate, String posterPath) {
        this.id = id;
        this.title = title;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
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


}