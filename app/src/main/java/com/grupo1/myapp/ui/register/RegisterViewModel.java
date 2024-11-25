package com.grupo1.myapp.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.grupo1.myapp.data.Result;
import com.grupo1.myapp.R;


import com.grupo1.myapp.data.RegisterRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterViewModel extends ViewModel {

    private final MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    private final MutableLiveData<RegisterResult> registerResult = new MutableLiveData<>();
    private final RegisterRepository registerRepository;

    RegisterViewModel(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    LiveData<RegisterResult> getRegisterResult() {
        return registerResult;
    }

    public void register(String username, String email, String password) {
        Result<Boolean> result = registerRepository.register(username, email, password);

        if (result instanceof Result.Success) {
            registerResult.setValue(new RegisterResult(true));
        } else {
            registerResult.setValue(new RegisterResult(R.string.register_failed));
        }
    }

    public void registerDataChanged(String username, String email, String password, String confirmPassword) {
        if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_username, null, null, null));
        } else if (!isEmailValid(email)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_email, null, null));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState(null, null, R.string.invalid_password, null));
        } else if (!password.equals(confirmPassword)) {
            registerFormState.setValue(new RegisterFormState(null, null, null, R.string.passwords_do_not_match));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }

    private boolean isUserNameValid(String username) {
        return username != null && !username.trim().isEmpty();
    }

    private boolean isEmailValid(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return password != null && password.trim().length() > 5 && matcher.matches();
    }
}
