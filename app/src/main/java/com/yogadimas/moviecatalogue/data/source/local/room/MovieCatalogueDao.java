package com.yogadimas.moviecatalogue.data.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;

import java.util.List;

@Dao
public interface MovieCatalogueDao {

    @Query("SELECT * FROM movies_entities")
    DataSource.Factory<Integer, MovieEntity> getMovies();

    @Query("SELECT * FROM movies_entities WHERE movieId = :movieId")
    LiveData<MovieEntity> getDetailMovie(int movieId);

    @Query("SELECT * FROM movies_entities WHERE favorite = 1")
    DataSource.Factory<Integer, MovieEntity> getFavoriteMovie();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MovieEntity> movieEntities);

    @Update
    void updateMovie(MovieEntity movieEntity);

    @Query("SELECT * FROM tv_show_entities")
    DataSource.Factory<Integer, TvShowEntity> getTvShows();

    @Query("SELECT * FROM tv_show_entities WHERE tvShowId= :tvShowId")
    LiveData<TvShowEntity> getDetailTvShow(int tvShowId);

    @Query("SELECT * FROM tv_show_entities WHERE favorite = 1")
    DataSource.Factory<Integer, TvShowEntity> getFavoriteTvShow();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvShows(List<TvShowEntity> tvShowEntities);

    @Update
    void updateTvShows(TvShowEntity tvShowEntity);

}
