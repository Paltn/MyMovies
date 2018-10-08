package com.example.pcardoso.mymoviesproject.view.glidebind;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUrlSetter {
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load("https://image.tmdb.org/t/p/w185"+url).into(view);
    }
}
