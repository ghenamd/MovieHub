package com.example.android.moviehub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.moviehub.model.Movie;
import com.example.android.moviehub.model.MovieResult;
import com.example.android.moviehub.network.RestManager;
import com.example.android.moviehub.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private static final int numberOfColumns = 2;
    private RestManager mManager;
    private static final String TAG = "MainActivity";
    private List<MovieResult> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        setRestManger();
        mAdapter = new MovieAdapter(new ArrayList<MovieResult>());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setRestManger() {
        mManager = new RestManager();
        Call<Movie> movieList = mManager.getMovieClient().getPopularMovies(Constants.API_KEY);
        movieList.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    List<MovieResult> list = response.body().getResults();
                    mAdapter.addMovie(list);

                } else {
                    int rc = response.code();
                    Log.v(TAG, String.valueOf(rc));
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }


        });
    }
}
