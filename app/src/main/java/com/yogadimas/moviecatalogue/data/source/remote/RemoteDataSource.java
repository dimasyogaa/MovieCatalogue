package com.yogadimas.moviecatalogue.data.source.remote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yogadimas.moviecatalogue.data.source.local.entity.movie.MovieEntity;
import com.yogadimas.moviecatalogue.data.source.local.entity.tvshow.TvShowEntity;
import com.yogadimas.moviecatalogue.data.source.remote.responses.movie.detail.DetailMovieResponse;
import com.yogadimas.moviecatalogue.data.source.remote.responses.movie.discover.MoviesResponse;
import com.yogadimas.moviecatalogue.data.source.remote.responses.tvshow.detail.DetailTvShowResponse;
import com.yogadimas.moviecatalogue.data.source.remote.responses.tvshow.discover.TvShowsResponse;
import com.yogadimas.moviecatalogue.network.ApiConfig;
import com.yogadimas.moviecatalogue.utils.EspressoIdlingResource;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {
    private static final String TAG = RemoteDataSource.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private static RemoteDataSource INSTANCE;
    public final Context context;

    public RemoteDataSource(Context context) {
        this.context = context;
    }

    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RemoteDataSource.class) {
                INSTANCE = new RemoteDataSource(context);
            }
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieEntity>>> getMovies() {
        EspressoIdlingResource.increment();
        Call<MoviesResponse> client = ApiConfig.getApiService().getDataMovies();
        MutableLiveData<ApiResponse<List<MovieEntity>>> resultMovies = new MutableLiveData<>();
        client.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(@NotNull Call<MoviesResponse> call,
                                   @NotNull Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            resultMovies.setValue(ApiResponse.success(response.body().getResults()));
                            EspressoIdlingResource.decrement();
                        }

                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "onFailure: " + response.body().getResults());
                        EspressoIdlingResource.decrement();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<MoviesResponse> call,
                                  @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                EspressoIdlingResource.decrement();
            }
        });
        return resultMovies;
    }

    public LiveData<ApiResponse<DetailMovieResponse>> getDetailMovie(int movieId) {
        EspressoIdlingResource.increment();
        Call<DetailMovieResponse> client = ApiConfig.getApiService().getDetailMovie(movieId);
        MutableLiveData<ApiResponse<DetailMovieResponse>> resultDetailMovies = new MutableLiveData<>();
        client.enqueue(new Callback<DetailMovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<DetailMovieResponse> call,
                                   @NotNull Response<DetailMovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultDetailMovies.setValue(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();


                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "onFailure: " + response.body());
                        EspressoIdlingResource.decrement();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<DetailMovieResponse> call,
                                  @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                EspressoIdlingResource.decrement();
            }
        });
        return resultDetailMovies;
    }

    public LiveData<ApiResponse<List<TvShowEntity>>> getTvShows() {
        EspressoIdlingResource.increment();
        Call<TvShowsResponse> client = ApiConfig.getApiService().getDataTvShows();
        MutableLiveData<ApiResponse<List<TvShowEntity>>> resultTvShows = new MutableLiveData<>();
        client.enqueue(new Callback<TvShowsResponse>() {
            @Override
            public void onResponse(@NotNull Call<TvShowsResponse> call,
                                   @NotNull Response<TvShowsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            resultTvShows.setValue(ApiResponse.success(response.body().getResults()));
                            EspressoIdlingResource.decrement();
                        }

                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "onFailure: " + response.body().getResults());
                        EspressoIdlingResource.decrement();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<TvShowsResponse> call,
                                  @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                EspressoIdlingResource.decrement();
            }
        });
        return resultTvShows;
    }


    public LiveData<ApiResponse<DetailTvShowResponse>> getDetailTvShow(int tvShowId) {
        EspressoIdlingResource.increment();
        Call<DetailTvShowResponse> client = ApiConfig.getApiService().getDetailTvShow(tvShowId);
        MutableLiveData<ApiResponse<DetailTvShowResponse>> resultDetailTvShow = new MutableLiveData<>();
        client.enqueue(new Callback<DetailTvShowResponse>() {
            @Override
            public void onResponse(@NotNull Call<DetailTvShowResponse> call,
                                   @NotNull Response<DetailTvShowResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultDetailTvShow.setValue(ApiResponse.success(response.body()));
                        EspressoIdlingResource.decrement();


                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "onFailure: " + response.body());
                        EspressoIdlingResource.decrement();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<DetailTvShowResponse> call,
                                  @NotNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                EspressoIdlingResource.decrement();
            }
        });
        return resultDetailTvShow;
    }


}
