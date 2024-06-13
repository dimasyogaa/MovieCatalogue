package com.yogadimas.moviecatalogue.ui.movie;


import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;

public interface MoviesFragmentCallback {
    void onShareClick(MovieEntity movieEntity);
}
