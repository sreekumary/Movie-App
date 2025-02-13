package com.example.movieapp.data.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.data.local.MovieDao;
import com.example.movieapp.data.local.MovieDatabase;
import com.example.movieapp.data.remote.MovieApiClient;
import com.example.movieapp.data.remote.MovieApiService;
import com.example.movieapp.model.MovieDetailResponse;
import com.example.movieapp.model.ResponseItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieDetailRepository {
    private final MovieDao movieDao;
    private final MovieApiService movieApiService;
    private final ExecutorService executorService;
    private final Handler mainHandler;
    public MovieDetailRepository(MovieDatabase database) {
        this.movieDao = database.movieDao();
        this.executorService = Executors.newSingleThreadExecutor(); // Runs operations in the background
        this.mainHandler = new Handler(Looper.getMainLooper()); // UI Thread Handler
        this.movieApiService = MovieApiClient.getInstance().create(MovieApiService.class);
    }

    // Fetch movie details from API
    public LiveData<MovieDetailResponse> getMovieDetails(String id) {
        MutableLiveData<MovieDetailResponse> movieData = new MutableLiveData<>();

        movieApiService.getMovieDetails(id).enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movieData.postValue(response.body());
                     insertMovie(response.body()); // movie details save to local DB
                }else{
                    Log.e("ERROR",response.toString());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                movieData.postValue(null);
            }
        });

        return movieData;
    }

    // Get favorite movie by ID from Room Database
    public LiveData<MovieDetailResponse> getFavoriteMovieById(String movieId) {
        return movieDao.getFavoriteMovieById(movieId);
    }

    public void insertMovie(MovieDetailResponse movie) {
        executorService.execute(() -> movieDao.insertMovie(movie));
    }
    // Get a specific movie from local DB
    public LiveData<MovieDetailResponse> getMovieById(String movieId) {
        return movieDao.getMovieById(movieId);
    }
    public void deleteMovie(MovieDetailResponse movie) {
        executorService.execute(() -> movieDao.deleteMovie(movie));
    }


}
