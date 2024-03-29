package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    //public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=6694827002af635fdcc124eac993364c";
    public static final String TAG = "MainActivity";
    public static final String KEY_ITEM_TITLE = "item_title";
    public static final String KEY_ITEM_OVERVIEW = "item_overview";
    public static final String KEY_ITEM_RATING = "item_rating";
    public static final String KEY_ITEM_POSTER = "item_poster";

    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);

        movies = new ArrayList<>();

        MovieAdapter.OnClickListener onClickListener = new MovieAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                //Log.i("DETAIL", movies.get(position).getTitle());
                Intent i = new Intent(MainActivity.this, MovieDetailActivity.class);
                i.putExtra(KEY_ITEM_TITLE, movies.get(position).getTitle());
                i.putExtra(KEY_ITEM_OVERVIEW, movies.get(position).getOverview());
                i.putExtra(KEY_ITEM_RATING, String.valueOf(movies.get(position).getRating()));
                i.putExtra(KEY_ITEM_POSTER, movies.get(position).getPosterPath());
                startActivity(i);
            }
        };

        // Instance the adapter
        MovieAdapter movieAdapter =  new MovieAdapter(this, movies, onClickListener);

        // Set the adapter to the recycler view
        rvMovies.setAdapter(movieAdapter);

        // Set a layout manager
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG, "Results: " + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Movies: " + movies.size());

                } catch (JSONException e) {
                    Log.e(TAG, "JSON Exception", e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.e(TAG, s, throwable);
            }
        });

    }
}