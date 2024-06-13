package com.yogadimas.moviecatalogue.ui.favorite.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;

public class TvShowFavoriteViewModel extends ViewModel {
    private final MovieCatalogueRepository movieCatalogueRepository;

    public TvShowFavoriteViewModel(MovieCatalogueRepository movieCatalogueRepository) {
        this.movieCatalogueRepository = movieCatalogueRepository;
    }

    public LiveData<PagedList<TvShowEntity>> getFavoriteTvShow() {
        return movieCatalogueRepository.getFavoriteTvShow();
    }

    void setFavoriteTvShow(TvShowEntity tvShowEntity) {
        final boolean newState = !tvShowEntity.isFavorite();
        movieCatalogueRepository.setFavoriteTvShow(tvShowEntity, newState);
    }
}
