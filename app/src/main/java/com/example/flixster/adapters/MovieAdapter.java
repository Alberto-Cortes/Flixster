package com.example.flixster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    // Used to inflate layout from XML and return holder.
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        // Get previously defined layout
        // TODO: MAKE LAYOUT PRETTIER USING CARDS AND ANIMATIONS
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Used to populate data to the view using the holder.
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    // Get size of list fed to view.
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // Class for local implementation of viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Logic part of visual elements
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        // Constructor for the class
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            // Match logic elements with their visual counterpart
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        // Bind an element of the JSON request result to a row of the RecyclerView
        public void bind(Movie movie) {
            // Simple texts
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            // Get image of URL using Glide
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
        }
    }
}
