package com.yogadimas.moviecatalogue.ui.detail.movie;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;

public class DetailMovieViewModel extends ViewModel {
    private final MovieCatalogueRepository movieCatalogueRepository;
    private final MutableLiveData<String> movieId = new MutableLiveData<>();
    public final LiveData<MovieEntity> getMovie = Transformations.switchMap(movieId,
            new Function<String, LiveData<MovieEntity>>() {
                @Override
                public LiveData<MovieEntity> apply(String id) {
                    return movieCatalogueRepository.getDetailMovie(Integer.parseInt(id));
                }
            });

    public DetailMovieViewModel(MovieCatalogueRepository mMovieCatalogueRepository) {
        movieCatalogueRepository = mMovieCatalogueRepository;
    }

    public void setMovieId(String movieId) {
        this.movieId.setValue(movieId);
    }

    void setFavoriteMovie() {
        MovieEntity movieEntity = getMovie.getValue();
        if (movieEntity != null) {
            final boolean newState = !movieEntity.isFavorite();
            movieCatalogueRepository.setFavoriteMovie(movieEntity, newState);

        }

    }


}
