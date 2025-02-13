package com.example.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.data.repo.MovieDetailRepository;
import com.example.movieapp.model.MovieDetailResponse;

import java.util.List;

public class MovieDetailViewModel extends ViewModel {
    private MovieDetailViewModel  movieDetailViewModel;
    private MutableLiveData<MovieDetailResponse> movieDetails;

    private LiveData<List<MovieDetailResponse>> allFavorites;
    private final MovieDetailRepository movieDetailRepository;

    // Constructor to inject repository
    public MovieDetailViewModel(MovieDetailRepository repository) {
        this.movieDetailRepository = repository;
    }

    // Fetch movie details by ID
    public LiveData<MovieDetailResponse> getMovieDetails(String id) {
        return movieDetailRepository.getMovieDetails(id);
    }
    // Get a specific movie from DB
    public LiveData<MovieDetailResponse> getMovieById(String movieId) {
        return movieDetailRepository.getMovieById(movieId);
    }
    // Get favorite movie by ID ID from Room Database
    public LiveData<MovieDetailResponse> getFavoriteMovieById(String movieId) {
        return movieDetailRepository.getFavoriteMovieById(movieId);
    }


    // Add movie to favorites
    public void addToFavorites(MovieDetailResponse movieDetail) {
        movieDetailRepository.insertMovie(movieDetail);
    }

    // Remove movie from favorites
    public void removeFromFavorites(MovieDetailResponse movieDetail) {
        movieDetailRepository.deleteMovie(movieDetail);
    }


}
