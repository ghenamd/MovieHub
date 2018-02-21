package com.example.android.moviehub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ghena on 20/02/2018.
 */

public class MovieResult {
    @Expose
    @SerializedName("poster_path")
    private String posterPath;
    @Expose
    @SerializedName("overview")
    private String overview;
    @Expose
    @SerializedName("release_date")
    private String releaseDate;
    @Expose
    @SerializedName("original_title")
    private String originalTitle;
    @Expose
    @SerializedName("vote_average")
    private String voteAverage;

    public MovieResult(String posterPath, String overview, String releaseDate, String originalTitle, String voteAverage) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.originalTitle = originalTitle;
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }
}
