package com.grupo1.myapp.ui.register;

import androidx.annotation.StringRes;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.grupo1.myapp.Principal;
import com.grupo1.myapp.databinding.ActivityRegisterBinding;
import com.grupo1.myapp.ui.login.LoginActivity;

import database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    private RegisterViewModel registerViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.grupo1.myapp.databinding.ActivityRegisterBinding binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        registerViewModel = new ViewModelProvider(this, new RegisterViewModelFactory())
                .get(RegisterViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText emailEditText = binding.email;
        final EditText passwordEditText = binding.password;
        final EditText confirmPasswordEditText = binding.confirmPassword;
        final Button registerButton = binding.register;
        final ProgressBar loadingProgressBar = binding.loading;

        registerViewModel.getRegisterFormState().observe(this, registerFormState -> {
            if (registerFormState == null) {
                return;
            }
            assert registerButton != null;
            registerButton.setEnabled(registerFormState.isDataValid());
            if (registerFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(registerFormState.getUsernameError()));
            }
            if (registerFormState.getEmailError() != null) {
                assert emailEditText != null;
                emailEditText.setError(getString(registerFormState.getEmailError()));
            }
            if (registerFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(registerFormState.getPasswordError()));
            }
            if (registerFormState.getConfirmPasswordError() != null) {
                assert confirmPasswordEditText != null;
                confirmPasswordEditText.setError(getString(registerFormState.getConfirmPasswordError()));
            }
        });

        registerViewModel.getRegisterResult().observe(this, registerResult -> {
            if (registerResult == null) {
                return;
            }
            loadingProgressBar.setVisibility(View.GONE);

            if (registerResult.getError() != null) {
                showRegisterFailed(registerResult.getError());
            }
            if (Boolean.TRUE.equals(registerResult.getSuccess())) {
            }

            try {
                DatabaseHelper db = new DatabaseHelper(this);
                if(db.verificarEmailExistente(emailEditText.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Usuario ya existe", Toast.LENGTH_LONG).show();
                }else {
                    assert emailEditText != null;
                    db.registrarUsuario(usernameEditText.getText().toString(), emailEditText.getText().toString().toLowerCase(), passwordEditText.getText().toString());
                    navigateToMainActivity();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                assert confirmPasswordEditText != null;
                assert emailEditText != null;
                registerViewModel.registerDataChanged(
                        usernameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        confirmPasswordEditText.getText().toString()
                );
            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        assert emailEditText != null;
        emailEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        assert confirmPasswordEditText != null;
        confirmPasswordEditText.addTextChangedListener(afterTextChangedListener);

        assert registerButton != null;
        registerButton.setOnClickListener(v ->

        {
            loadingProgressBar.setVisibility(View.VISIBLE);
            registerViewModel.register(
                    usernameEditText.getText().toString(),
                    emailEditText.getText().toString(),
                    passwordEditText.getText().toString()
            );
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void showRegisterFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
    public void login(View view){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
