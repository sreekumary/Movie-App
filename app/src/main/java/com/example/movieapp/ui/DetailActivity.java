package com.example.movieapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.data.local.MovieDatabase;
import com.example.movieapp.data.repo.MovieDetailRepository;
import com.example.movieapp.model.MovieDetailResponse;
import com.example.movieapp.viewmodel.MovieDetailViewModel;
import com.example.movieapp.viewmodel.MovieDetailViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DetailActivity extends AppCompatActivity {
    private MovieDetailViewModel movieDetailViewModel;
    private String movieId;
    private boolean isFavorite = false;
    private MovieDetailResponse movieDetail;
    private ImageView movieImage;
    private TextView movieTitle, synopsis, cast, rating, releaseDate;
    private FloatingActionButton favoriteIcon;  // Floating Action Button
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize Views
        movieImage = findViewById(R.id.movie_image);
        movieTitle = findViewById(R.id.movie_title);
        synopsis = findViewById(R.id.synopsis);
        cast = findViewById(R.id.cast);
        rating = findViewById(R.id.rating);
        releaseDate = findViewById(R.id.release_date);
        favoriteIcon = findViewById(R.id.favorite_fab);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable back button
        }

         // Initialize Repository & ViewModel
         MovieDetailRepository repository = new MovieDetailRepository(MovieDatabase.getInstance(this));
         movieDetailViewModel = new ViewModelProvider(this, new MovieDetailViewModelFactory(repository))
                 .get(MovieDetailViewModel.class);


         // Get Movie ID from Intent
         Intent intent = getIntent();
         if (intent != null && intent.hasExtra("MOVIE_ID")) {
             movieId = intent.getStringExtra("MOVIE_ID");
             fetchMovieDetails(movieId);
         }

         // Handle Favorite Icon Click
         favoriteIcon.setOnClickListener(v -> toggleFavorite());
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    // Fetch movie details from ViewModel
    private void fetchMovieDetails(String id) {
        movieDetailViewModel.getFavoriteMovieById(id).observe(this, favoriteMovie -> {
            if (favoriteMovie != null) {
                //fetch favourites from db
                movieDetail = favoriteMovie;
                isFavorite = true;
                favoriteIcon.setImageResource(R.drawable.ic_favorite_filled); // Set filled icon
                Log.d("TAG", "Favorite movie found: db " + favoriteMovie.getPrimaryTitle());
                Log.d("TAG", "isFavorite: " + isFavorite);
                updateUI(movieDetail);

            } else {
                movieDetailViewModel.getMovieDetails(id).observe(this, response -> {
                    if (response != null) {
                        movieDetail = response;
                        isFavorite = false;
                        favoriteIcon.setImageResource(R.drawable.ic_favorite_border); // Set empty icon
                        Log.d("TAG", "Movie not in favorites");
                        updateUI(response);

                    }
                });
            }

        });
    }

      //Update UI with movie details
    private void updateUI(MovieDetailResponse movie) {
        movieTitle.setText(movie.getPrimaryTitle());
        synopsis.setText(movie.getDescription());
        rating.setText("Rating: " + movie.getAverageRating());
        releaseDate.setText("Release Date: " + movie.getReleaseDate());

        // Load image
        Glide.with(this)
                .load(movie.getPrimaryImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(movieImage);

        // Display cast members
        if (movie.getCast() != null && !movie.getCast().isEmpty()) {
            StringBuilder castList = new StringBuilder();
            for (int i = 0; i < movie.getCast().size(); i++) {
                castList.append(movie.getCast().get(i).getFullName());
                if (i != movie.getCast().size() - 1) {
                    castList.append(", ");
                }
            }
            cast.setText("Cast: " + castList.toString());
        } else {
            cast.setText("Cast: Not available");
        }
    }

   private void toggleFavorite() {
       if (movieDetail != null) {
           if (isFavorite) {
               movieDetailViewModel.removeFromFavorites(movieDetail);
               isFavorite = false;
               favoriteIcon.setImageResource(R.drawable.ic_favorite_border);
               Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
           } else {
               movieDetailViewModel.addToFavorites(movieDetail);
               isFavorite = true;
               favoriteIcon.setImageResource(R.drawable.ic_favorite_filled);
               Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
           }

       }
   }


}
