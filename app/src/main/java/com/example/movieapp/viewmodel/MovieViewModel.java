package com.example.movieapp.viewmodel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.data.repo.MovieRepository;
import com.example.movieapp.model.ResponseItem;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MovieRepository repository;
    private MutableLiveData<List<ResponseItem>> moviesLiveData;
    private MutableLiveData<String> errorLiveData; // To handle error state (e.g., no network)

    private final MutableLiveData<List<ResponseItem>> searchResults = new MutableLiveData<>();


    public MovieViewModel() {
        repository = new MovieRepository();
        moviesLiveData = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();
    }
    public LiveData<List<ResponseItem>> getSearchResults() {
        return searchResults;
    }
    public void searchMovies(String query) {
        repository.searchMovies(query, searchResults);
    }
    // Expose the movie data as LiveData
    public LiveData<List<ResponseItem>> getMoviesLiveData() {
        return moviesLiveData;
    }

    // Expose the error message as LiveData
    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    // Fetch movies data from the repository
    public void loadMovies(int page, int limit, Context context) {
        if (isNetworkAvailable(context)) {
            repository.getMovies(page, limit).observeForever(new Observer<List<ResponseItem>>() {
                @Override
                public void onChanged(List<ResponseItem> responseItems) {
                    if (responseItems != null && !responseItems.isEmpty()) {
                        moviesLiveData.setValue(responseItems);
                    } else {
                        // Handle error if response is empty
                        errorLiveData.setValue("No movies found.");
                    }
                }
            });
        } else {
            // If no network, show an error message
            errorLiveData.setValue("No network available.");
        }
    }

    // Method to check if the device has network connectivity
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
