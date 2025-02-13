package com.example.movieapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import com.example.movieapp.model.MovieDetailResponse;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie_details WHERE id = :movieId")
    LiveData<MovieDetailResponse> getFavoriteMovieById(String movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieDetailResponse movie);//inserting in db

    @Delete
    void deleteMovie(MovieDetailResponse movie);

    @Query("SELECT * FROM movie_details")
    LiveData<List<MovieDetailResponse>> getAllFavorites();//Fetch all favourites

    @Query("SELECT * FROM movie_details WHERE id = :movieId")
    LiveData<MovieDetailResponse> getMovieById(String movieId); // Fetch movie details


}