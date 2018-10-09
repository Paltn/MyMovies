package com.example.pcardoso.mymoviesproject.model;

import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.google.gson.annotations.SerializedName;

public class Movie {
    private int id;
    //title
    @SerializedName("original_title")
    private String originalTitle;
    //desc
    @SerializedName("poster_path")
    private String imageMovie;
    private String overview;
    private double popularity;

    public Movie() {
    }

    public int getIdMovie() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie(int id, String originalTitle, String imageMovie, String overview, double popularity) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.imageMovie = imageMovie;
        this.overview = overview;
        this.popularity = popularity;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    //@Bindable
    public String getImageMovie() {
        return imageMovie;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setImageMovie(String imageMovie) {
        this.imageMovie = imageMovie;
    }

    public static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Movie>() {
                @Override
                public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {

                    return oldItem.id == newItem.id;

                }

                @Override
                public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {

                    return oldItem.getImageMovie().equals(newItem.getImageMovie())

                            && oldItem.getOriginalTitle().equals(newItem.getOriginalTitle());


                }

            };

}
