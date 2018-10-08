package com.example.pcardoso.mymoviesproject.engine.implementation;

import android.arch.paging.PageKeyedDataSource;
import android.nfc.Tag;
import android.util.Log;

import com.example.pcardoso.mymoviesproject.BaseConstants;
import com.example.pcardoso.mymoviesproject.engine.MovieController;
import com.example.pcardoso.mymoviesproject.engine.client.MovieClient;
import com.example.pcardoso.mymoviesproject.model.Movie;
import com.example.pcardoso.mymoviesproject.model.Pagination;
import com.example.pcardoso.mymoviesproject.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class MovieImplementation implements MovieController {

    //api_key
    static String apiKey = "a80a43e78f3ee8b2f2af8b30858778bf";
    static String url = "https://api.themoviedb.org/3/";

    @Override
    public void list(final int page, final PageKeyedDataSource.LoadInitialCallback<Integer, Movie> callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieClient service = retrofit.create(MovieClient.class);

        service.listMovies(apiKey, page).enqueue(new Callback<Pagination<Movie>>() {
            @Override
            public void onResponse(Call<Pagination<Movie>> call, Response<Pagination<Movie>> response) {
                callback.onResult(response.body().getResults(), null, page + 1);

                Log.d("Movie", "My response: " + response);

            }

            @Override
            public void onFailure(Call<Pagination<Movie>> call, Throwable t) {
                Log.d("Movie", "My responseFA: " + t.getMessage());

            }
        });
    }

    @Override
    public void listAfter(final int page, final PageKeyedDataSource.LoadCallback callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieClient service = retrofit.create(MovieClient.class);

        Call<Pagination<Movie>> call = service.listMovies(apiKey, page);

        call.enqueue(new Callback<Pagination<Movie>>() {
            @Override
            public void onResponse(Call<Pagination<Movie>> call, Response<Pagination<Movie>> response) {
                if (response.body() != null) {

                    Integer nextPage = (page > 0) ? page + 1 : null;

                    callback.onResult(response.body().getResults(), nextPage);
                }
            }

            @Override
            public void onFailure(Call<Pagination<Movie>> call, Throwable t) {
            }
        });
    }
}
