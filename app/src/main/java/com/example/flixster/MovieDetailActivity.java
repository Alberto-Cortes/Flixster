package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixster.databinding.ActivityMovieDetailBinding;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailActivity extends AppCompatActivity {

    /* Commented as using Viewbinding instead of regular binding by id lookup.
    ImageView ivDetail;
    TextView tvTitleDetail;
    TextView tvOverviewDetail;
    RatingBar ratingDetail;
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Bind using Viewbinding
        ActivityMovieDetailBinding binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_movie_detail);

        // Get extra data from the intent
        String detailTitle = getIntent().getStringExtra(MainActivity.KEY_ITEM_TITLE);
        String detailOverview = getIntent().getStringExtra(MainActivity.KEY_ITEM_OVERVIEW);
        float detailRating = Float.parseFloat(getIntent().getStringExtra(MainActivity.KEY_ITEM_RATING));
        String detailPoster = getIntent().getStringExtra(MainActivity.KEY_ITEM_POSTER);

        // Connect visual and logic elements of detail view
        /*ivDetail = findViewById(R.id.ivDetail);
        tvOverviewDetail = findViewById(R.id.tvOverviewDetail);
        tvTitleDetail = findViewById(R.id.tvTitleDetail);
        ratingDetail = findViewById(R.id.ratingDetail);

        tvOverviewDetail.setText(detailOverview);
        tvTitleDetail.setText(detailTitle);
        ratingDetail.setRating(detailRating/2);*/

        binding.tvOverviewDetail.setText(detailOverview);
        binding.tvTitleDetail.setText(detailTitle);
        binding.ratingDetail.setRating(detailRating/2);

        Log.i("DETAIL_VIEW", "MANAGED TO SET DATA OF INTENT "+ detailRating );

        Glide.with(this)
                .load(detailPoster)
                .placeholder(R.drawable.flicks_backdrop_placeholder)
                .transform(new RoundedCornersTransformation(30, 10))
                .into(binding.ivDetail);
    }
}