package com.grupo1.myapp.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.grupo1.myapp.data.LoginDataSource;
import com.grupo1.myapp.data.LoginRepository;

import database.DatabaseHelper;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private DatabaseHelper dbHelper;

    public LoginViewModelFactory(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource(dbHelper)));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
