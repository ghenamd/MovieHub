package com.example.android.moviehub.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.moviehub.R;
import com.example.android.moviehub.model.Movie;
import com.example.android.moviehub.model.MovieResult;
import com.example.android.moviehub.network.RestManager;
import com.example.android.moviehub.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnItemClicked {
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private static final int numberOfColumns = 2;
    private RestManager mManager;
    private static final String TAG = "MainActivity";
    private ProgressBar mProgressBar;
    private TextView mStatus;
    private GridLayoutManager layoutManager;
    private static final String RECYCLERVIEW_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mStatus = findViewById(R.id.status);
        layoutManager = new GridLayoutManager(this, numberOfColumns);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        ifConnected();
        mAdapter = new MovieAdapter(new ArrayList<MovieResult>(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    //This method fetches the Upcoming Movies form the API and displays
    // them when the app is started for the first time
    private void setRestManger() {
        mManager = new RestManager();
        Call<Movie> movieList = mManager.getMovieClient().getUpcomingMovies(Constants.API_KEY);
        movieList.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    List<MovieResult> list = response.body().getResults();
                    mAdapter.addMovieResult(list);
                    mProgressBar.setVisibility(View.INVISIBLE);

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

    // Interface method that allowes the user to click on the Movies Image and open a
    //new Activity with the details of the selected  movie
    @Override
    public void onMovieClicked(MovieResult movie) {
        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
        intent.putExtra(getString(R.string.object), movie);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Menu that sorts the fetched movies by Popularity or Rating
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // We sort the Movies by Top Rated
        if (item.getItemId() == R.id.order_by_top_rated) {
            mManager = new RestManager();
            Call<Movie> movieList = mManager.getMovieClient().getTopRatedMovies(Constants.API_KEY);
            movieList.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if (response.isSuccessful()) {
                        List<MovieResult> list = response.body().getResults();
                        mAdapter.addMovieResult(list);
                        mProgressBar.setVisibility(View.INVISIBLE);

                    } else {
                        int rc = response.code();
                        Log.v(TAG, String.valueOf(rc));
                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                }
            });
            //Sorting Movies by Popularity
        }
        if (item.getItemId() == R.id.order_by_popularity) {
            mManager = new RestManager();
            Call<Movie> movieList = mManager.getMovieClient().getPopularMovies(Constants.API_KEY);
            movieList.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if (response.isSuccessful()) {
                        List<MovieResult> list = response.body().getResults();
                        mAdapter.addMovieResult(list);
                        mProgressBar.setVisibility(View.INVISIBLE);

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

        return true;
    }

    //This method checks if there is Network if there is we fetch the data from internet
    // otherwise we inform the user of an Unavailable Network
    private boolean isConnected() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    //If the user is connected to the Internet we set the RestManger
    private void ifConnected() {
        if (isConnected()) {
            setRestManger();
        } else {
            mStatus.setText(R.string.unavailable_network);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            if(mAdapter !=null){
                if(mAdapter.getItemCount()>0){ mRecyclerView.getLayoutManager()
                        .onRestoreInstanceState(savedInstanceState.getParcelable(RECYCLERVIEW_POSITION));}
            }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECYCLERVIEW_POSITION, mRecyclerView.getLayoutManager().onSaveInstanceState());
    }



}
