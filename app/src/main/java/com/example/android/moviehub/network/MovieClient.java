package com.example.android.moviehub.network;

import com.example.android.moviehub.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ghena on 19/02/2018.
 */

public interface MovieClient {

    @GET("popular?")
    Call<Movie> getPopularMovies(@Query("api_key") String apiKey);
    @GET("top_rated?")
    Call<Movie> getTopRatedMovies(@Query("api_key") String apiKey);
    @GET("upcoming?")
    Call<Movie> getUpcomingMovies(@Query("api_key")String apiKey);
}
