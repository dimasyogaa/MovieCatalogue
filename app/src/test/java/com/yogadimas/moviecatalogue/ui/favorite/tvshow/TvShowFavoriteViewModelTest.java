package com.yogadimas.moviecatalogue.ui.favorite.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;

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
public class TvShowFavoriteViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TvShowFavoriteViewModel viewModel;
    @Mock
    private MovieCatalogueRepository academyRepository;

    @Mock
    private Observer<PagedList<TvShowEntity>> observer;

    @Mock
    private PagedList<TvShowEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new TvShowFavoriteViewModel(academyRepository);
    }

    @Test
    public void getFavoriteTvShow() {
        PagedList<TvShowEntity> dummyTvShow = pagedList;
        when(dummyTvShow.size()).thenReturn(3);
        MutableLiveData<PagedList<TvShowEntity>> tvShows = new MutableLiveData<>();
        tvShows.setValue(dummyTvShow);
        when(academyRepository.getFavoriteTvShow()).thenReturn(tvShows);
        List<TvShowEntity> tvShowEntities = viewModel.getFavoriteTvShow().getValue();
        verify(academyRepository).getFavoriteTvShow();
        assertNotNull(tvShowEntities);
        assertEquals(3, tvShowEntities.size());
        viewModel.getFavoriteTvShow().observeForever(observer);
        verify(observer).onChanged(dummyTvShow);
    }

}