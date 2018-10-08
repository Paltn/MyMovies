package com.example.pcardoso.mymoviesproject.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.bumptech.glide.Glide;
import com.example.pcardoso.mymoviesproject.engine.datasource.DataSourceFactory;
import com.example.pcardoso.mymoviesproject.engine.datasource.MoviesDataSource;
import com.example.pcardoso.mymoviesproject.model.Movie;

public class ListMoviesViewModel extends ViewModel {

    public LiveData<PagedList<Movie>> movies;

    public ListMoviesViewModel() {
        DataSourceFactory dataSourceFactory = new DataSourceFactory(new MoviesDataSource());
        //   leaguePagedList = new LivePagedListBuilder<>(dataSourceFactory, DataSourceUtil.setPagedListConfig(3))
        //                .build();

        PagedList.Config config =  new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(21)
                .setPrefetchDistance(6)
                .build();

        movies = new LivePagedListBuilder<>(dataSourceFactory,  config)
            .build();
         }


}
