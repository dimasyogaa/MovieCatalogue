package com.yogadimas.moviecatalogue.data.source.local.entity.movie;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies_entities")
public class MovieEntity {

    @PrimaryKey
    @ColumnInfo(name = "movieId")
    @SerializedName("id")
    private final int movieId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private final String title;

    @ColumnInfo(name = "voteAverage")
    @SerializedName("vote_average")
    private final double voteAverage;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private final String overview;

    @ColumnInfo(name = "release")
    @SerializedName("release_date")
    private final String release;

    @ColumnInfo(name = "imagePath")
    @SerializedName("poster_path")
    private final String imagePath;

    @ColumnInfo(name = "favorite")
    private boolean favorite = false;

    public MovieEntity(int movieId, String title, double voteAverage, String overview, String release, String imagePath, Boolean favorite) {
        this.movieId = movieId;
        this.title = title;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.release = release;
        this.imagePath = imagePath;
        if (favorite != null) {
            this.favorite = favorite;
        }
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease() {
        return release;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

}
