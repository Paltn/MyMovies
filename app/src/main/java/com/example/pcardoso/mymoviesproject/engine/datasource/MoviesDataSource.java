package com.example.pcardoso.mymoviesproject.engine.datasource;

import android.arch.paging.PageKeyedDataSource;
import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.example.pcardoso.mymoviesproject.BaseConstants;
import com.example.pcardoso.mymoviesproject.engine.implementation.MovieImplementation;
import com.example.pcardoso.mymoviesproject.model.Movie;

public class MoviesDataSource extends PageKeyedDataSource<Integer, Movie> {

    private MovieImplementation movieImplementation;



    public MoviesDataSource(){
        movieImplementation= new MovieImplementation();

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        movieImplementation.list(1, callback);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    movieImplementation.listAfter(params.key, callback);
    }

    public static boolean isConnected (Context context) {

        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork != null && activeNetwork.isConnected()){
            return true;
        }

        return false;
}




}
