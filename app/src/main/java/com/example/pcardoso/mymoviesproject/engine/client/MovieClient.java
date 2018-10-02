package com.example.pcardoso.mymoviesproject.engine.client;

import com.example.pcardoso.mymoviesproject.model.Movie;
import com.example.pcardoso.mymoviesproject.model.Pagination;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieClient {

    //annotion
    @GET("movie/popular")
    //RETORNO LISTMOVIES
    Call<Pagination<Movie>> listMovies(@Query("api_key") String apiKey);
}


