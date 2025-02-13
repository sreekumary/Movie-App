package com.example.movieapp.data.remote;

import com.example.movieapp.model.MovieDetailResponse;
import com.example.movieapp.model.ResponseItem;
import com.example.movieapp.model.ResponseMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {
    //movie list
    @GET("imdb/top250-movies")
    Call<List<ResponseItem>> getMovies(@Query("page") int page, @Query("limit") int limit);

    //movie detail page
    @GET("imdb/{id}")  // Replace with your actual API endpoint
    Call<MovieDetailResponse> getMovieDetails(@Path("id") String movieId);

    //search function autocomplete
    @GET("imdb/autocomplete")
    Call<List<ResponseItem>> searchMovies(@Query("query") String query);
}
