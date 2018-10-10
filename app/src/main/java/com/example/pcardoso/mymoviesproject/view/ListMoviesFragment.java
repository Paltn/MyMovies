package com.example.pcardoso.mymoviesproject.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pcardoso.mymoviesproject.R;
import com.example.pcardoso.mymoviesproject.databinding.FragmentListMoviesBinding;
import com.example.pcardoso.mymoviesproject.model.Movie;
import com.example.pcardoso.mymoviesproject.view.adapter.MoviesAdapter;
import com.example.pcardoso.mymoviesproject.view.callback.MovieClickCallback;
import com.example.pcardoso.mymoviesproject.viewmodel.ListMoviesViewModel;

import java.util.List;

public class ListMoviesFragment extends Fragment {
    OnMovieSelectedListener activityCallback;

    private FragmentListMoviesBinding binding;
    private List<Movie> movieList;
    private ListMoviesViewModel viewModel;
    private MoviesAdapter adapter;

    private final MovieClickCallback movieClickCallback = movie -> activityCallback.onMovieSelected(movie);

    public interface OnMovieSelectedListener {
        void onMovieSelected(Movie movie);
    }

    public ListMoviesFragment() {
        // Required empty public constructor
    }

    //private final MovieClickCallback movieClickCallback = movie -> activityCallback.onMovieSelected(movie);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_movies, container, false);
        viewModel = ViewModelProviders.of(this).get(ListMoviesViewModel.class);
        recycle();
        adapter = new MoviesAdapter(movieClickCallback);

        viewModel.movies.observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(@Nullable PagedList<Movie> movies) {
                adapter.submitList(movies);
            }
        });

        viewModel.noInternet.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Toast.makeText(getActivity(), "No connection, please check your network.", Toast.LENGTH_SHORT).show();
            }
        });
        binding.recyMovie.setAdapter(adapter);
        return binding.getRoot();
    }

    public void recycle() {

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int columns = Math.round(dpWidth/100);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), columns);
        gridLayoutManager.setReverseLayout(false);
       // gridLayoutManager.setStackFromEnd(false);
        binding.recyMovie.setLayoutManager(gridLayoutManager);
    }

}
