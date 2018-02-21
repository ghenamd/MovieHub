package com.example.android.moviehub;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.moviehub.model.MovieResult;
import com.example.android.moviehub.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ghena on 16/02/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieResult> mMovieList;
    public MovieAdapter(List<MovieResult> list) {
        mMovieList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieResult currentMovie = mMovieList.get(position);
        String imagePath = Constants.IMAGE_BASE_URL + currentMovie.getPosterPath();
        Picasso.with(holder.itemView.getContext()).load(imagePath).fit().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void addMovie(List<MovieResult> movies){
        mMovieList = movies;
        notifyDataSetChanged();
    }
    public MovieResult getMovieResult(int position){
        return mMovieList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
        }
    }
}
