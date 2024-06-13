package com.yogadimas.moviecatalogue.ui.tvshow;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
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
public class TvShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TvShowViewModel tvShowViewModel;
    @Mock
    private MovieCatalogueRepository movieCatalogueRepository;

    @Mock
    private Observer<Resource<PagedList<TvShowEntity>>> observerTvShow;

    @Mock
    private PagedList<TvShowEntity> pagedList;

    @Before
    public void setUp() {
        tvShowViewModel = new TvShowViewModel(movieCatalogueRepository);
    }

    @Test
    public void getTvShow() {
        Resource<PagedList<TvShowEntity>> dummyTvShow = Resource.success(pagedList);
        when(dummyTvShow.data.size()).thenReturn(3);
        MutableLiveData<Resource<PagedList<TvShowEntity>>> tvShows = new MutableLiveData<>();
        tvShows.setValue(dummyTvShow);
        when(movieCatalogueRepository.getTvShows()).thenReturn(tvShows);

        List<TvShowEntity> tvShowEntities = tvShowViewModel.getTvShow().getValue().data;
        verify(movieCatalogueRepository).getTvShows();
        assertNotNull(tvShowEntities);
        assertEquals(3, tvShowEntities.size());
        tvShowViewModel.getTvShow().observeForever(observerTvShow);
        verify(observerTvShow).onChanged(dummyTvShow);
    }
}