package com.yogadimas.moviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.yogadimas.moviecatalogue.data.source.local.LocalDataSource;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.data.source.remote.RemoteDataSource;
import com.yogadimas.moviecatalogue.data.source.remote.responses.movie.detail.DetailMovieResponse;
import com.yogadimas.moviecatalogue.data.source.remote.responses.tvshow.detail.DetailTvShowResponse;
import com.yogadimas.moviecatalogue.utils.AppExecutors;
import com.yogadimas.moviecatalogue.utils.DataDummy;
import com.yogadimas.moviecatalogue.utils.LiveDataTestUtil;
import com.yogadimas.moviecatalogue.utils.PagedListUtil;
import com.yogadimas.moviecatalogue.vo.Resource;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieCatalogueRepositoryTest {

    private final RemoteDataSource remoteDataSource = mock(RemoteDataSource.class);
    private final LocalDataSource localDataSource = mock(LocalDataSource.class);
    private final AppExecutors appExecutors = mock(AppExecutors.class);

    private final FakeMovieCatalogueRepository fakeMovieCatalogueRepository =
            new FakeMovieCatalogueRepository(remoteDataSource, localDataSource, appExecutors);

    private final ArrayList<MovieEntity> movieEntities = DataDummy.generateDummyMovies();
    private final int movieId = movieEntities.get(0).getMovieId();
    private final DetailMovieResponse detailMovieResponse = DataDummy.remoteGenerateDummyDetailMovie();

    private final ArrayList<TvShowEntity> tvShowEntities = DataDummy.generateDummyTvShow();
    private final int tvShowId = tvShowEntities.get(0).getTvShowId();
    private final DetailTvShowResponse detailTvShowResponses = DataDummy.remoteGenerateDummyDetailTvShow();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void getMovies() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getMovies()).thenReturn(dataSourceFactory);
        fakeMovieCatalogueRepository.getMovies();

        Resource<PagedList<MovieEntity>> moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()));
        verify(localDataSource).getMovies();

        assertNotNull(moviesEntities.data);
        assertEquals(movieEntities.size(), moviesEntities.data.size());
    }

    @Test
    public void getDetailMovie() {
        MutableLiveData<MovieEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(DataDummy.generateDummyDetailMovie(false));
        when(localDataSource.getDetailMovie(movieId)).thenReturn(dummyEntity);

        MovieEntity movie = LiveDataTestUtil.getValue(fakeMovieCatalogueRepository.getDetailMovie(movieId));
        verify(localDataSource).getDetailMovie(movieId);

        assertNotNull(movie);

        assertNotNull(movie.getTitle());

        assertEquals(detailMovieResponse.getTitle(), movie.getTitle());

    }

    @Test
    public void getFavoriteMovie() {

        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getFavoriteMovie()).thenReturn(dataSourceFactory);
        fakeMovieCatalogueRepository.getFavoriteMovie();

        Resource<PagedList<MovieEntity>> movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()));

        verify(localDataSource).getFavoriteMovie();

        assertNotNull(movieEntities);
        assertEquals(this.movieEntities.size(), movieEntities.data.size());
    }

    @Test
    public void getTvShows() {
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getTvShow()).thenReturn(dataSourceFactory);
        fakeMovieCatalogueRepository.getTvShows();

        Resource<PagedList<TvShowEntity>> tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()));
        verify(localDataSource).getTvShow();

        assertNotNull(tvShowEntities.data);
        assertEquals(this.tvShowEntities.size(), tvShowEntities.data.size());
    }

    @Test
    public void getDetailTvShow() {
        MutableLiveData<TvShowEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(DataDummy.generateDummyDetailTvShow(false));
        when(localDataSource.getDetailTvShow(tvShowId)).thenReturn(dummyEntity);

        TvShowEntity tvShow = LiveDataTestUtil.getValue(fakeMovieCatalogueRepository.getDetailTvShow(tvShowId));
        verify(localDataSource).getDetailTvShow(tvShowId);

        assertNotNull(tvShow);

        assertNotNull(tvShow.getTitle());

        assertEquals(detailTvShowResponses.getName(), tvShow.getTitle());

    }

    @Test
    public void getFavoriteTvShow() {
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getFavoriteTvShow()).thenReturn(dataSourceFactory);
        fakeMovieCatalogueRepository.getFavoriteTvShow();

        Resource<PagedList<TvShowEntity>> tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()));

        verify(localDataSource).getFavoriteTvShow();

        assertNotNull(tvShowEntities);
        assertEquals(this.tvShowEntities.size(), tvShowEntities.data.size());
    }

}