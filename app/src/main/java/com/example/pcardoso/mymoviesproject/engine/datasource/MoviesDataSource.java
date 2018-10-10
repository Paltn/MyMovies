package com.example.pcardoso.mymoviesproject.engine.datasource;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.content.Context;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.pcardoso.mymoviesproject.engine.implementation.MovieImplementation;
import com.example.pcardoso.mymoviesproject.model.Movie;
import com.example.pcardoso.mymoviesproject.util.NetworkUtil;

public class MoviesDataSource extends PageKeyedDataSource<Integer, Movie> {

    private MovieImplementation movieImplementation;
    public MutableLiveData<Boolean> networkStatus;
    Context context;

    public MoviesDataSource(Context context){
        movieImplementation= new MovieImplementation();
        this.context = context;
        networkStatus = new MutableLiveData<>();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        if(NetworkUtil.isConnected(context)) {
            movieImplementation.list(1, callback);
        } else {
            Log.d("Network", "No network connectivity, will not search for movies.");
            networkStatus.postValue(true);
            //avisa o usuario
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        if(NetworkUtil.isConnected(context)){
            movieImplementation.listAfter(params.key, callback);
        } else {
            Log.d("Network", "No network connectivity, will not search for movies.");
            networkStatus.postValue(true);
            //Avisa o usuario
        }

    }

     public void testConnection(@NonNull LoadCallback<Integer, Movie> callback) {
            movieImplementation.tesConn(callback);
    }

}
