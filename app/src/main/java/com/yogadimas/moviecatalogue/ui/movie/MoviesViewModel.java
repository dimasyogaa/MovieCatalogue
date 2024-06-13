package com.yogadimas.moviecatalogue.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.vo.Resource;

public class MoviesViewModel extends ViewModel {
    private final MovieCatalogueRepository movieCatalogueRepository;

    public MoviesViewModel(MovieCatalogueRepository mMovieCatalogueRepository) {
        movieCatalogueRepository = mMovieCatalogueRepository;
    }

    public LiveData<Resource<PagedList<MovieEntity>>> getMovies() {
        return movieCatalogueRepository.getMovies();
    }
}
