package com.example.android.moviehub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moviehub.R;
import com.example.android.moviehub.model.MovieResult;
import com.example.android.moviehub.utils.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by Ghena on 16/02/2018.
 */

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView mImageView;
    private TextView title;
    private TextView userRating;
    private TextView releaseDate;
    private TextView overview;
    private static final String TAG = "MovieDetailActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        setTitle(getString(R.string.movie_activity_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mImageView = findViewById(R.id.image_thumbnail);
        title = findViewById(R.id.title);
        userRating = findViewById(R.id.user_rating);
        releaseDate = findViewById(R.id.release_date);
        overview = findViewById(R.id.overview);

        Intent intent = getIntent();
        MovieResult movieResult =(MovieResult) intent.getSerializableExtra(getString(R.string.object));
        String image = Constants.IMAGE_BASE_URL_LARGE + movieResult.getPosterPath();
        Log.v(TAG,image);

        Picasso.with(this).load(image).fit().into(mImageView);
        title.setText(movieResult.getOriginalTitle());
        userRating.setText(movieResult.getVoteAverage());
        releaseDate.setText(movieResult.getReleaseDate());
        overview.setText(movieResult.getOverview());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
