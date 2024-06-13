package com.yogadimas.moviecatalogue.ui.detail.movie;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailMovieViewModelTest {
    private final int movieId = DataDummy.generateDummyMovies().get(0).getMovieId();
    private final String movId = Integer.toString(movieId);
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private DetailMovieViewModel detailMovieViewModel;
    @Mock
    private MovieCatalogueRepository movieCatalogueRepository;

    @Mock
    private Observer<MovieEntity> movieEntityObserver;

    @Before
    public void setUp() {
        detailMovieViewModel = new DetailMovieViewModel(movieCatalogueRepository);
        detailMovieViewModel.setMovieId(movId);
    }

    @Test
    public void getMovie() {
        double delta = 0.0001;
        MovieEntity dummyDetailMovie = DataDummy.generateDummyDetailMovie(true);
        MutableLiveData<MovieEntity> movie = new MutableLiveData<>();
        movie.setValue(dummyDetailMovie);

        when(movieCatalogueRepository.getDetailMovie(movieId)).thenReturn(movie);

        detailMovieViewModel.getMovie.observeForever(movieEntityObserver);

        MovieEntity movieEntity = detailMovieViewModel.getMovie.getValue();

        verify(movieCatalogueRepository).getDetailMovie(movieId);

        assertNotNull(movieEntity);
        assertEquals(dummyDetailMovie.getMovieId(), movieEntity.getMovieId());
        assertEquals(dummyDetailMovie.getImagePath(), movieEntity.getImagePath());
        assertEquals(dummyDetailMovie.getVoteAverage(), movieEntity.getVoteAverage(), delta);
        assertEquals(dummyDetailMovie.getRelease(), movieEntity.getRelease());
        assertEquals(dummyDetailMovie.getTitle(), movieEntity.getTitle());
        assertEquals(dummyDetailMovie.getOverview(), movieEntity.getOverview());
        assertEquals(dummyDetailMovie.isFavorite(), movieEntity.isFavorite());
        verify(movieEntityObserver).onChanged(dummyDetailMovie);
    }

}