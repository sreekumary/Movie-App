package com.example.movieapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.data.local.MovieDatabase;
import com.example.movieapp.data.repo.FavoriteMoviesRepository;
import com.example.movieapp.data.repo.MovieDetailRepository;
import com.example.movieapp.model.MovieDetailResponse;
import com.example.movieapp.model.ResponseItem;
import com.example.movieapp.viewmodel.FavoriteMoviesViewModel;
import com.example.movieapp.viewmodel.FavoriteMoviesViewModelFactory;
import com.example.movieapp.viewmodel.MovieDetailViewModel;
import com.example.movieapp.viewmodel.MovieDetailViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    private MovieDetailViewModel movieDetailViewModel;
    private RecyclerView recyclerView;
    private FavoriteMoviesAdapter favoriteMoviesAdapter;
    private FavoriteMoviesViewModel favoriteMoviesViewModel;

    private List<MovieDetailResponse> movieList = new ArrayList<>();
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerView = findViewById(R.id.favRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        favoriteMoviesAdapter = new FavoriteMoviesAdapter(movieList,this);
        recyclerView.setAdapter(favoriteMoviesAdapter);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable back button
        }


        FavoriteMoviesRepository repository = new FavoriteMoviesRepository(MovieDatabase.getInstance(this));
        favoriteMoviesViewModel = new ViewModelProvider(this, new FavoriteMoviesViewModelFactory(repository))
                .get(FavoriteMoviesViewModel.class);


        // Observe Favorites List
        favoriteMoviesViewModel.getAllFavorites().observe(this, favoriteMovies -> {
            favoriteMoviesAdapter.setMovies(favoriteMovies);
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}