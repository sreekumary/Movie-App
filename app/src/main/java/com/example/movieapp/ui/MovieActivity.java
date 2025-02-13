package com.example.movieapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.model.ResponseItem;
import com.example.movieapp.viewmodel.MovieViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {
    private MovieAdapter adapter;
    private MovieViewModel movieViewModel;
    private final List<ResponseItem> movieList = new ArrayList<>();
    private int page = 1;
    private final int limit = 10;
    private RecyclerView searchRecyclerView, recyclerView;
    private SearchAdapter searchAdapter;
    private final List<ResponseItem> searchResults = new ArrayList<>();
    private AutoCompleteTextView searchView;
    private TextInputLayout searchInputLayout;
    private TextInputEditText searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.movieRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        searchInputLayout = findViewById(R.id.searchInput);
        searchInput = (TextInputEditText) searchInputLayout.getEditText(); // Get the TextInputEditText directly

        //movie set in recyclerview
        adapter = new MovieAdapter(movieList, this);
        recyclerView.setAdapter(adapter);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        searchRecyclerView = findViewById(R.id.searchRecyclerView);
        searchRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        //search results display in recycler view
        searchAdapter = new SearchAdapter(searchResults, this, movie -> {
            openMovieDetails(movie);
        });

        searchRecyclerView.setAdapter(searchAdapter);

        // Observe search results
        movieViewModel.getSearchResults().observe(this, results -> {
            if (results != null && !results.isEmpty()) {
                searchResults.clear();
                searchResults.addAll(results);
                searchAdapter.notifyDataSetChanged();
                searchRecyclerView.setVisibility(View.VISIBLE);
            } else {
                searchRecyclerView.setVisibility(View.GONE);
            }
        });

        // Handle search input
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                if (query.length() >= 2) {
                    movieViewModel.searchMovies(query.toString());
                } else {
                    searchRecyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });



        // Observe movie data from the ViewModel
        movieViewModel.getMoviesLiveData().observe(this, responseItems -> {
            if (responseItems != null && !responseItems.isEmpty()) {
                movieList.addAll(responseItems);  // Add the new movies to the list
                adapter.notifyDataSetChanged(); // Notify adapter of new data
                page++; // Increment page for next load
            }
        });

        // Observe error messages from the ViewModel
        movieViewModel.getErrorLiveData().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(MovieActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // Load movies from ViewModel
        loadMovies();
    }

    private void openMovieDetails(ResponseItem movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("movie_id", movie.getId());
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    // Handle Menu Clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            showSearchBar();
            return true;
        } else if (item.getItemId() == R.id.action_favorites) {
            openFavoritesScreen();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSearchBar() {
        findViewById(R.id.searchInput).setVisibility(View.VISIBLE);
        searchInput.requestFocus();
    }

    private void openFavoritesScreen() {
        Toast.makeText(this, "Favorites Clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }
    private void loadMovies() {
        movieViewModel.loadMovies(page, limit, this);
    }
}
