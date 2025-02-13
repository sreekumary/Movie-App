package com.example.movieapp.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.movieapp.data.repo.FavoriteMoviesRepository;

public class FavoriteMoviesViewModelFactory implements ViewModelProvider.Factory {

    private final FavoriteMoviesRepository repository;

    public FavoriteMoviesViewModelFactory(FavoriteMoviesRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavoriteMoviesViewModel.class)) {
            return (T) new FavoriteMoviesViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
