package com.example.android.moviehub.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.moviehub.R;
import com.example.android.moviehub.model.MovieResult;
import com.example.android.moviehub.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ghena on 16/02/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>  {

    private List<MovieResult> mMovieList;
    private OnItemClicked mOnItemClicked;
    public MovieAdapter(List<MovieResult> list,OnItemClicked clicked) {
        mMovieList = list;
        mOnItemClicked = clicked;
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



    public interface OnItemClicked{

        void onMovieClicked(MovieResult movie);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void addMovieResult(List<MovieResult> movies){
        mMovieList = movies;
        notifyDataSetChanged();
    }
    public MovieResult getMovieResult(int position){
        return mMovieList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            MovieResult result = mMovieList.get(position);
            mOnItemClicked.onMovieClicked(result);

        }
    }
}
