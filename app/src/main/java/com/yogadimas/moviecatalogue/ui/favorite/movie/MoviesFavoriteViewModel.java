package com.yogadimas.moviecatalogue.ui.favorite.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;

public class MoviesFavoriteViewModel extends ViewModel {
    private final MovieCatalogueRepository movieCatalogueRepository;

    public MoviesFavoriteViewModel(MovieCatalogueRepository movieCatalogueRepository) {
        this.movieCatalogueRepository = movieCatalogueRepository;
    }

    public LiveData<PagedList<MovieEntity>> getFavoriteMovie() {
        return movieCatalogueRepository.getFavoriteMovie();
    }

    void setFavoriteMovie(MovieEntity movieEntity) {
        final boolean newState = !movieEntity.isFavorite();
        movieCatalogueRepository.setFavoriteMovie(movieEntity, newState);
    }
}
