package com.yogadimas.moviecatalogue.ui.movie;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.vo.Resource;

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
public class MoviesViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MoviesViewModel moviesViewModel;
    @Mock
    private MovieCatalogueRepository movieCatalogueRepository;

    @Mock
    private Observer<Resource<PagedList<MovieEntity>>> observerMovie;

    @Mock
    private PagedList<MovieEntity> pagedList;

    @Before
    public void setUp() {
        moviesViewModel = new MoviesViewModel(movieCatalogueRepository);
    }

    @Test
    public void getMovies() {
        Resource<PagedList<MovieEntity>> dummyMovies = Resource.success(pagedList);
        when(dummyMovies.data.size()).thenReturn(3);
        MutableLiveData<Resource<PagedList<MovieEntity>>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);
        when(movieCatalogueRepository.getMovies()).thenReturn(movies);

        List<MovieEntity> movieEntities = moviesViewModel.getMovies().getValue().data;
        verify(movieCatalogueRepository).getMovies();
        assertNotNull(movieEntities);
        assertEquals(3, movieEntities.size());
        moviesViewModel.getMovies().observeForever(observerMovie);
        verify(observerMovie).onChanged(dummyMovies);
    }
}