package com.grupo1.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.grupo1.myapp.ui.login.LoginActivity;
import com.grupo1.myapp.ui.register.RegisterActivity;


public class MainActivity extends AppCompatActivity {
    public static int waitTime = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, waitTime);

    }
}