package com.yogadimas.moviecatalogue.ui.detail.tvshow;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;

public class DetailTvShowViewModel extends ViewModel {
    private final MovieCatalogueRepository movieCatalogueRepository;
    private final MutableLiveData<String> tvShowId = new MutableLiveData<>();
    public final LiveData<TvShowEntity> getTvShow = Transformations.switchMap(tvShowId,
            new Function<String, LiveData<TvShowEntity>>() {
                @Override
                public LiveData<TvShowEntity> apply(String id) {
                    return movieCatalogueRepository.getDetailTvShow(Integer.parseInt(id));
                }
            });


    public DetailTvShowViewModel(MovieCatalogueRepository mMovieCatalogueRepository) {
        movieCatalogueRepository = mMovieCatalogueRepository;
    }

    public void setTvShowId(String tvShowId) {
        this.tvShowId.setValue(tvShowId);
    }

    void setFavoriteTvShow() {
        TvShowEntity tvShowEntity = getTvShow.getValue();
        if (tvShowEntity != null) {
            final boolean newState = !tvShowEntity.isFavorite();
            movieCatalogueRepository.setFavoriteTvShow(tvShowEntity, newState);

        }

    }
}
