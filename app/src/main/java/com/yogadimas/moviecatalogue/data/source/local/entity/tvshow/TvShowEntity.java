package com.yogadimas.moviecatalogue.data.source.local.entity.tvshow;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tv_show_entities")
public class TvShowEntity {
    @PrimaryKey
    @ColumnInfo(name = "tvShowId")
    @SerializedName("id")
    private final int tvShowId;

    @ColumnInfo(name = "title")
    @SerializedName("name")
    private final String title;

    @ColumnInfo(name = "voteAverage")
    @SerializedName("vote_average")
    private final double voteAverage;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private final String overview;

    @ColumnInfo(name = "firstAirDate")
    @SerializedName("first_air_date")
    private final String firstAirDate;

    @ColumnInfo(name = "imagePath")
    @SerializedName("poster_path")
    private final String imagePath;
    @ColumnInfo(name = "favorite")
    private boolean favorite = false;

    public TvShowEntity(int tvShowId, String title, double voteAverage, String overview, String firstAirDate, String imagePath, Boolean favorite) {
        this.tvShowId = tvShowId;
        this.title = title;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.firstAirDate = firstAirDate;
        this.imagePath = imagePath;
        if (favorite != null) {
            this.favorite = favorite;
        }
    }


    public int getTvShowId() {
        return tvShowId;
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


    public String getFirstAirDate() {
        return firstAirDate;
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
