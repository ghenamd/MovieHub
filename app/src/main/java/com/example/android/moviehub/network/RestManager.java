package com.example.android.moviehub.network;

import com.example.android.moviehub.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ghena on 19/02/2018.
 */

public class RestManager {
    private MovieClient mMovieClient;

    public MovieClient getMovieClient(){

        if (mMovieClient == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mMovieClient = retrofit.create(MovieClient.class);
        }
        return mMovieClient;
    }
}
