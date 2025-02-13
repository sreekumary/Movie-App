package com.example.movieapp.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.model.MovieDetailResponse;

import java.util.List;

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.FavoriteViewHolder> {
    private List<MovieDetailResponse> favoriteMovies;
    private Context context;

    public FavoriteMoviesAdapter(List<MovieDetailResponse> favoriteMovies, Context context) {
        this.favoriteMovies = favoriteMovies;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        MovieDetailResponse movie = favoriteMovies.get(position);

        // Set movie title
        holder.title.setText(movie.getPrimaryTitle());



        Glide.with(context)
                .load(movie.getPrimaryImage())
                .placeholder(R.drawable.ic_launcher_background) // Placeholder image while loading
                .error(R.drawable.ic_launcher_background) // Image to show if loading fails
                .into(holder.image);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("MOVIE_ID", movie.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return favoriteMovies.size();
    }

    public void setMovies(List<MovieDetailResponse> newFavorites) {
        this.favoriteMovies = newFavorites;
        notifyDataSetChanged(); // Refresh RecyclerView
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView title, year;
        ImageView image;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            year = itemView.findViewById(R.id.movie_year);
            image = itemView.findViewById(R.id.movie_image);
        }
    }
}
