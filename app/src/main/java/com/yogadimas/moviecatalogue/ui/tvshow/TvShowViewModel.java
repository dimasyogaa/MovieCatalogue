package com.yogadimas.moviecatalogue.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.vo.Resource;

public class TvShowViewModel extends ViewModel {
    private final MovieCatalogueRepository movieCatalogueRepository;

    public TvShowViewModel(MovieCatalogueRepository mMovieCatalogueRepository) {
        movieCatalogueRepository = mMovieCatalogueRepository;
    }

    public LiveData<Resource<PagedList<TvShowEntity>>> getTvShow() {
        return movieCatalogueRepository.getTvShows();
    }
}
