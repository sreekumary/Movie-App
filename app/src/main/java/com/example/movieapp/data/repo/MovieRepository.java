package com.example.movieapp.data.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.data.remote.MovieApiClient;
import com.example.movieapp.data.remote.MovieApiService;
import com.example.movieapp.model.ResponseItem;
import com.example.movieapp.model.ResponseMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

        private MovieApiService movieApiService;

        public MovieRepository() {
            movieApiService = MovieApiClient.getInstance().create(MovieApiService.class);
        }


       public LiveData<List<ResponseItem>> getMovies(int page, int limit) {
           MutableLiveData<List<ResponseItem>> data = new MutableLiveData<>();
           movieApiService.getMovies(page, limit).enqueue(new Callback<List<ResponseItem>>() {
               @Override
               public void onResponse(@NonNull Call<List<ResponseItem>> call, @NonNull Response<List<ResponseItem>> response) {
                   if (response.isSuccessful()) {
                       data.setValue(response.body());
                   }
               }

               @Override
               public void onFailure(@NonNull Call<List<ResponseItem>> call, Throwable t) {
                   Log.e("responseFailure", String.valueOf(t));
                   data.setValue(null);
               }
           });
           return data;
       }



    public void searchMovies(String query, MutableLiveData<List<ResponseItem>> searchResults) {
        movieApiService.searchMovies(query).enqueue(new Callback<List<ResponseItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<ResponseItem>> call, @NonNull Response<List<ResponseItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    searchResults.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ResponseItem>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to fetch search results", t);
            }
        });
    }


}
