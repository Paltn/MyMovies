package com.example.pcardoso.mymoviesproject.engine.implementation;

import android.arch.paging.PageKeyedDataSource;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.example.pcardoso.mymoviesproject.BaseConstants;
import com.example.pcardoso.mymoviesproject.BuildConfig;
import com.example.pcardoso.mymoviesproject.engine.MovieController;
import com.example.pcardoso.mymoviesproject.engine.client.MovieClient;
import com.example.pcardoso.mymoviesproject.engine.datasource.MoviesDataSource;
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

    String apiKey = BuildConfig.ApiKey;

    static String url = "https://api.themoviedb.org/3/";

    Context context;
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
    public void tesConn(PageKeyedDataSource.LoadCallback callback) {

        //MoviesDataSource moviesDataSource = new MoviesDataSource();
        //moviesDataSource.testConnection(callback);

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");
            Toast toast = Toast.makeText(context, " Internet Connection", Toast.LENGTH_LONG);
            toast.show();
            //return true;
        } else {
            Toast toast = Toast.makeText(context, " NO Internet Connection", Toast.LENGTH_LONG);
            toast.show();
            // return false;
        }

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
