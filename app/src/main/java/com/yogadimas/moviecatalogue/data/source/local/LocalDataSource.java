package com.yogadimas.moviecatalogue.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.data.source.local.room.MovieCatalogueDao;

import java.util.List;

public class LocalDataSource {
    private static LocalDataSource INSTANCE;
    private final MovieCatalogueDao mMovieCatalogueDao;

    public LocalDataSource(MovieCatalogueDao mMovieCatalogueDao) {
        this.mMovieCatalogueDao = mMovieCatalogueDao;
    }

    public static LocalDataSource getInstance(MovieCatalogueDao movieCatalogueDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(movieCatalogueDao);
        }
        return INSTANCE;
    }

    public DataSource.Factory<Integer, MovieEntity> getMovies() {
        return mMovieCatalogueDao.getMovies();
    }

    public LiveData<MovieEntity> getDetailMovie(int id) {
        return mMovieCatalogueDao.getDetailMovie(id);
    }

    public DataSource.Factory<Integer, MovieEntity> getFavoriteMovie() {
        return mMovieCatalogueDao.getFavoriteMovie();
    }

    public void setFavoriteMovie(MovieEntity movie, boolean newState) {
        movie.setFavorite(newState);
        mMovieCatalogueDao.updateMovie(movie);
    }

    public void insertMovies(List<MovieEntity> movieEntities) {
        mMovieCatalogueDao.insertMovies(movieEntities);
    }

    public DataSource.Factory<Integer, TvShowEntity> getTvShow() {
        return mMovieCatalogueDao.getTvShows();
    }


    public LiveData<TvShowEntity> getDetailTvShow(int id) {
        return mMovieCatalogueDao.getDetailTvShow(id);
    }

    public DataSource.Factory<Integer, TvShowEntity> getFavoriteTvShow() {
        return mMovieCatalogueDao.getFavoriteTvShow();
    }

    public void setFavoriteTvShow(TvShowEntity tvShow, boolean newState) {
        tvShow.setFavorite(newState);
        mMovieCatalogueDao.updateTvShows(tvShow);
    }


    public void insertTvShow(List<TvShowEntity> tvShowEntities) {
        mMovieCatalogueDao.insertTvShows(tvShowEntities);
    }

}
