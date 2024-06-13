package com.yogadimas.moviecatalogue.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.yogadimas.moviecatalogue.data.source.local.LocalDataSource;
import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.data.source.remote.ApiResponse;
import com.yogadimas.moviecatalogue.data.source.remote.RemoteDataSource;
import com.yogadimas.moviecatalogue.utils.AppExecutors;
import com.yogadimas.moviecatalogue.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class FakeMovieCatalogueRepository implements MovieCatalogueDataSource {

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

    public FakeMovieCatalogueRepository(@NonNull RemoteDataSource remoteDataSource,
                                        @NonNull LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }


    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getMovies() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieEntity>>(appExecutors) {

            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getMovies(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieEntity>>> createCall() {
                return remoteDataSource.getMovies();
            }

            @Override
            protected void saveCallResult(List<MovieEntity> data) {
                ArrayList<MovieEntity> movieEntities = new ArrayList<>();
                for (MovieEntity movieEntity : data) {
                    MovieEntity movie = new MovieEntity(
                            movieEntity.getMovieId(),
                            movieEntity.getTitle(),
                            movieEntity.getVoteAverage(),
                            movieEntity.getOverview(),
                            movieEntity.getRelease(),
                            movieEntity.getImagePath(),
                            false);
                    movieEntities.add(movie);
                }
                localDataSource.insertMovies(movieEntities);
            }
        }.asLiveData();
    }


    @Override
    public LiveData<MovieEntity> getDetailMovie(int movieId) {
        return localDataSource.getDetailMovie(movieId);
    }

    @Override
    public LiveData<PagedList<MovieEntity>> getFavoriteMovie() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoriteMovie(), config).build();
    }

    @Override
    public void setFavoriteMovie(MovieEntity movie, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setFavoriteMovie(movie, state));

    }

    @Override
    public LiveData<Resource<PagedList<TvShowEntity>>> getTvShows() {
        return new NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowEntity>>(appExecutors) {

            @Override
            protected LiveData<PagedList<TvShowEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getTvShow(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TvShowEntity>>> createCall() {
                return remoteDataSource.getTvShows();
            }

            @Override
            protected void saveCallResult(List<TvShowEntity> data) {
                ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();
                for (TvShowEntity tvShowEntity : data) {
                    TvShowEntity tvShow = new TvShowEntity(
                            tvShowEntity.getTvShowId(),
                            tvShowEntity.getTitle(),
                            tvShowEntity.getVoteAverage(),
                            tvShowEntity.getOverview(),
                            tvShowEntity.getFirstAirDate(),
                            tvShowEntity.getImagePath(),
                            false);
                    tvShowEntities.add(tvShow);
                }
                localDataSource.insertTvShow(tvShowEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<TvShowEntity> getDetailTvShow(int tvShowId) {
        return localDataSource.getDetailTvShow(tvShowId);
    }

    @Override
    public LiveData<PagedList<TvShowEntity>> getFavoriteTvShow() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoriteTvShow(), config).build();
    }

    @Override
    public void setFavoriteTvShow(TvShowEntity tvShow, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setFavoriteTvShow(tvShow, state));
    }
}
