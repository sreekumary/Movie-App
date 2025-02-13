package com.example.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.movieapp.data.repo.FavoriteMoviesRepository;
import com.example.movieapp.model.MovieDetailResponse;
import java.util.List;

public class FavoriteMoviesViewModel extends ViewModel {
    private final FavoriteMoviesRepository repository;
    private final LiveData<List<MovieDetailResponse>> favoriteMovies;

    public FavoriteMoviesViewModel(FavoriteMoviesRepository repository) {
        this.repository = repository;
        this.favoriteMovies = repository.getAllFavorites();
    }

    public LiveData<List<MovieDetailResponse>> getAllFavorites() {
        return favoriteMovies;
    }
}
