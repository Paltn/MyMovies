package com.example.pcardoso.mymoviesproject.engine;

import android.arch.paging.PageKeyedDataSource;

import com.example.pcardoso.mymoviesproject.model.Movie;

import java.util.List;

import retrofit2.Call;


//define a assinatura de metodos
//define contratos a serem implementados
public interface MovieController {
    void list(int page, PageKeyedDataSource.LoadInitialCallback<Integer, Movie> callback);
    void deleteMovie();
}
