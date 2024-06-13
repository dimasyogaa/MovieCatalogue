package com.yogadimas.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.vo.Resource;

public interface MovieCatalogueDataSource {
    LiveData<Resource<PagedList<MovieEntity>>> getMovies();

    LiveData<MovieEntity> getDetailMovie(int movieId);

    LiveData<PagedList<MovieEntity>> getFavoriteMovie();

    void setFavoriteMovie(MovieEntity movie, boolean state);

    LiveData<Resource<PagedList<TvShowEntity>>> getTvShows();

    LiveData<TvShowEntity> getDetailTvShow(int tvShowId);

    LiveData<PagedList<TvShowEntity>> getFavoriteTvShow();

    void setFavoriteTvShow(TvShowEntity tvShow, boolean state);


}
