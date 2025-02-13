package com.example.movieapp.data.repo;

import androidx.lifecycle.LiveData;

import com.example.movieapp.data.local.MovieDao;
import com.example.movieapp.data.local.MovieDatabase;
import com.example.movieapp.model.MovieDetailResponse;
import java.util.List;

public class FavoriteMoviesRepository {
    private final MovieDatabase movieDatabase;
    private final MovieDao movieDao;
    public FavoriteMoviesRepository(MovieDatabase database) {
        this.movieDatabase = database;
        this.movieDao = database.movieDao();
    }

    public LiveData<List<MovieDetailResponse>> getAllFavorites() {
        return movieDao.getAllFavorites();
    }
}
