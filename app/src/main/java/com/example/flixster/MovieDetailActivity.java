package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    //Context context;
    ImageView ivDetail;
    TextView tvTitleDetail;
    TextView tvOverviewDetail;
    RatingBar ratingDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Connect visual and logic elements of detail view
        ivDetail = findViewById(R.id.ivDetail);
        tvOverviewDetail = findViewById(R.id.tvOverviewDetail);
        tvTitleDetail = findViewById(R.id.tvTitleDetail);
        ratingDetail = findViewById(R.id.ratingDetail);

        // Get extra data from the intent
        String detailTitle = getIntent().getStringExtra(MainActivity.KEY_ITEM_TITLE);
        String detailOverview = getIntent().getStringExtra(MainActivity.KEY_ITEM_OVERVIEW);
        int detailRating = Integer.parseInt(getIntent().getStringExtra(MainActivity.KEY_ITEM_RATING));
        String detailPoster = getIntent().getStringExtra(MainActivity.KEY_ITEM_POSTER);

        //Log.i("DETAIL_VIEW", "MANAGED TO GET DATA OF INTENT");

        tvOverviewDetail.setText(detailOverview);
        tvTitleDetail.setText(detailTitle);
        ratingDetail.setRating(detailRating);
        ratingDetail.setStepSize(detailRating);

        Log.i("DETAIL_VIEW", "MANAGED TO SET DATA OF INTENT");

        Glide.with(this)
                .load(detailPoster)
                .placeholder(R.drawable.flicks_backdrop_placeholder)
                .into(ivDetail);
    }
}