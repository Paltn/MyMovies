package com.example.pcardoso.mymoviesproject.viewmodel;

import android.app.Application;
import android.app.Service;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bumptech.glide.Glide;
import com.example.pcardoso.mymoviesproject.engine.datasource.DataSourceFactory;
import com.example.pcardoso.mymoviesproject.engine.datasource.MoviesDataSource;
import com.example.pcardoso.mymoviesproject.model.Movie;

public class ListMoviesViewModel extends AndroidViewModel {

    public LiveData<PagedList<Movie>> movies;
    DataSourceFactory dataSourceFactory;
    DataSource<Integer, Movie> dataSource;
    public LiveData<Boolean> noInternet;

    public ListMoviesViewModel(Application application) {
        super(application);
        dataSourceFactory = new DataSourceFactory(new MoviesDataSource(getApplication().getApplicationContext()));
        //   leaguePagedList = new LivePagedListBuilder<>(dataSourceFactory, DataSourceUtil.setPagedListConfig(3))
        //                .build();
        PagedList.Config config =  new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(21)
                .setPrefetchDistance(6)
                .build();

        dataSource = dataSourceFactory.create();
        transformation();

        movies = new LivePagedListBuilder<>(dataSourceFactory,  config)
            .build();
         }

    private void transformation(){
        noInternet = Transformations.switchMap(dataSourceFactory.getLiveDataSource(),
                dataSource -> ((MoviesDataSource) dataSource).networkStatus);
    }

}
