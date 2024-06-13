package com.yogadimas.moviecatalogue.ui.detail.tvshow;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.yogadimas.moviecatalogue.data.source.MovieCatalogueRepository;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
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
public class DetailTvShowViewModelTest {
    private final int tvShowId = DataDummy.generateDummyTvShow().get(0).getTvShowId();
    private final String tvId = Integer.toString(tvShowId);
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private DetailTvShowViewModel detailTvShowViewModel;
    @Mock
    private MovieCatalogueRepository movieCatalogueRepository;

    @Mock
    private Observer<TvShowEntity> tvShowEntityObserver;

    @Before
    public void setUp() {
        detailTvShowViewModel = new DetailTvShowViewModel(movieCatalogueRepository);
        detailTvShowViewModel.setTvShowId(tvId);
    }


    @Test
    public void getTvShow() {
        double delta = 0.0001;
        TvShowEntity dummyDetailTvShow = DataDummy.generateDummyDetailTvShow(true);
        MutableLiveData<TvShowEntity> tvShow = new MutableLiveData<>();
        tvShow.setValue(dummyDetailTvShow);
        when(movieCatalogueRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow);
        detailTvShowViewModel.getTvShow.observeForever(tvShowEntityObserver);
        TvShowEntity tvShowEntity = detailTvShowViewModel.getTvShow.getValue();
        verify(movieCatalogueRepository).getDetailTvShow(tvShowId);
        assertNotNull(tvShowEntity);
        assertEquals(tvShowId, tvShowEntity.getTvShowId());
        assertEquals(dummyDetailTvShow.getImagePath(), tvShowEntity.getImagePath());
        assertEquals(dummyDetailTvShow.getVoteAverage(), tvShowEntity.getVoteAverage(), delta);
        assertEquals(dummyDetailTvShow.getFirstAirDate(), tvShowEntity.getFirstAirDate());
        assertEquals(dummyDetailTvShow.getTitle(), tvShowEntity.getTitle());
        assertEquals(dummyDetailTvShow.getOverview(), tvShowEntity.getOverview());
        assertEquals(dummyDetailTvShow.isFavorite(), tvShowEntity.isFavorite());
        verify(tvShowEntityObserver).onChanged(dummyDetailTvShow);
    }
}