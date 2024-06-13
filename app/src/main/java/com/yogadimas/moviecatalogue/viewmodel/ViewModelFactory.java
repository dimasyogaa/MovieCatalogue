package com.yogadimas.moviecatalogue.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.di.Injection;
import com.yogadimas.moviecatalogue.ui.detail.movie.DetailMovieViewModel;
import com.yogadimas.moviecatalogue.ui.detail.tvshow.DetailTvShowViewModel;
import com.yogadimas.moviecatalogue.ui.favorite.movie.MoviesFavoriteViewModel;
import com.yogadimas.moviecatalogue.ui.favorite.tvshow.TvShowFavoriteViewModel;
import com.yogadimas.moviecatalogue.ui.movie.MoviesViewModel;
import com.yogadimas.moviecatalogue.ui.tvshow.TvShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;
    private final MovieCatalogueRepository mMovieCatalogueRepository;

    private ViewModelFactory(MovieCatalogueRepository movieCatalogueRepository) {
        mMovieCatalogueRepository = movieCatalogueRepository;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoviesViewModel.class)) {
            return (T) new MoviesViewModel(mMovieCatalogueRepository);
        } else if (modelClass.isAssignableFrom(TvShowViewModel.class)) {
            return (T) new TvShowViewModel(mMovieCatalogueRepository);
        } else if (modelClass.isAssignableFrom(DetailMovieViewModel.class)) {
            return (T) new DetailMovieViewModel(mMovieCatalogueRepository);
        } else if (modelClass.isAssignableFrom(DetailTvShowViewModel.class)) {
            return (T) new DetailTvShowViewModel(mMovieCatalogueRepository);
        } else if (modelClass.isAssignableFrom(MoviesFavoriteViewModel.class)) {
            return (T) new MoviesFavoriteViewModel(mMovieCatalogueRepository);
        } else if (modelClass.isAssignableFrom(TvShowFavoriteViewModel.class)) {
            return (T) new TvShowFavoriteViewModel(mMovieCatalogueRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
