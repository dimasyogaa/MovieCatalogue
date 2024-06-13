package com.yogadimas.moviecatalogue.ui.favorite.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoviesFavoriteViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MoviesFavoriteViewModel viewModel;
    @Mock
    private MovieCatalogueRepository academyRepository;

    @Mock
    private Observer<PagedList<MovieEntity>> observer;


    @Mock
    private PagedList<MovieEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new MoviesFavoriteViewModel(academyRepository);
    }

    @Test
    public void getFavoriteMovie() {
        PagedList<MovieEntity> dummyMovies = pagedList;
        when(dummyMovies.size()).thenReturn(3);
        MutableLiveData<PagedList<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);
        when(academyRepository.getFavoriteMovie()).thenReturn(movies);
        List<MovieEntity> movieEntities = viewModel.getFavoriteMovie().getValue();
        verify(academyRepository).getFavoriteMovie();
        assertNotNull(movieEntities);
        assertEquals(3, movieEntities.size());
        viewModel.getFavoriteMovie().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}
