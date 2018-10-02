package com.example.pcardoso.mymoviesproject.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pcardoso.mymoviesproject.R;
import com.example.pcardoso.mymoviesproject.databinding.ActivityMainBinding;
import com.example.pcardoso.mymoviesproject.engine.implementation.MovieImplementation;
import com.example.pcardoso.mymoviesproject.model.Movie;
import com.example.pcardoso.mymoviesproject.view.adapter.MoviesAdapter;
import com.example.pcardoso.mymoviesproject.viewmodel.ListMoviesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity   {

    private ActivityMainBinding binding;
    private List<Movie> movieList;
    private ListMoviesViewModel viewModel;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(ListMoviesViewModel.class);

        adapter= new MoviesAdapter();

        viewModel.movies.observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(@Nullable PagedList<Movie> movies) {
                adapter.submitList(movies);
            }
        });


        binding.recyMovie.setAdapter(adapter);

        recycle();
    }

    public void recycle() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setReverseLayout(false);
        gridLayoutManager.setStackFromEnd(false);
        binding.recyMovie.setLayoutManager(gridLayoutManager);
    }



}
