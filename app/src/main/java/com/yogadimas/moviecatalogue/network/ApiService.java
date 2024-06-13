package com.yogadimas.moviecatalogue.network;

import com.yogadimas.moviecatalogue.BuildConfig;
import com.yogadimas.moviecatalogue.data.source.remote.responses.movie.detail.DetailMovieResponse;
import com.yogadimas.moviecatalogue.data.source.remote.responses.movie.discover.MoviesResponse;
import com.yogadimas.moviecatalogue.data.source.remote.responses.tvshow.detail.DetailTvShowResponse;
import com.yogadimas.moviecatalogue.data.source.remote.responses.tvshow.discover.TvShowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("discover/movie?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    Call<MoviesResponse> getDataMovies();

    @GET("movie/{movie_id}?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    Call<DetailMovieResponse> getDetailMovie(
            @Path("movie_id") int movieId);


    @GET("discover/tv?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    Call<TvShowsResponse> getDataTvShows();

    @GET("tv/{tv_id}?api_key=" + BuildConfig.API_KEY + "&language=en-US")
    Call<DetailTvShowResponse> getDetailTvShow(
            @Path("tv_id") int tvShowId);
}
