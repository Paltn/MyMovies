package com.example.pcardoso.mymoviesproject.view.adapter;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pcardoso.mymoviesproject.R;
import com.example.pcardoso.mymoviesproject.databinding.ActivityMainBinding;
import com.example.pcardoso.mymoviesproject.databinding.ListmoviesBinding;
import com.example.pcardoso.mymoviesproject.model.Movie;


public class MoviesAdapter extends PagedListAdapter<Movie, MoviesAdapter.ViewHolder> {


    public MoviesAdapter() {
        super(Movie.DIFF_CALLBACK);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        ListmoviesBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.listmovies, viewGroup, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.binding.setMovie(getItem(i));
        viewHolder.binding.executePendingBindings();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final ListmoviesBinding binding;

        public ViewHolder(ListmoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}