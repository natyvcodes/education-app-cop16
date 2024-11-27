package com.grupo1.myapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceFragmentCompat;

import com.grupo1.myapp.data.LoginRepository;
import com.grupo1.myapp.data.model.LoggedInUser;
import com.grupo1.myapp.ui.login.LoginActivity;

public class SettingsActivity extends AppCompatActivity {
    private Button inicio_fin;
    private TextView back;
    private TextView estado;
    private TextView nombre;
    private TextView correo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        inicio_fin = findViewById(R.id.btn_inifi);
        estado = findViewById(R.id.estado);
        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        back = findViewById(R.id.back);
        SharedPreferences sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPref.getBoolean("isLoggedIn", false);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, Principal.class);
                startActivity(intent);
                finish();
            }
        });
        if (isLoggedIn) {
            inicio_fin.setText("Cerrar sesion");
            estado.setText("Activo");
            nombre.setText("Nombre:" );
            inicio_fin.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Red));
            inicio_fin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    logout();
                }
            });
        }else {
            inicio_fin.setText("Iniciar Sesion");
            inicio_fin.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.Green));
            nombre.setVisibility(View.GONE);
            correo.setVisibility(View.GONE);
            estado.setText("Inactivo, no ha iniciado sesion");
            inicio_fin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signin();
                }
            });
        }
    }
    private void logout() {

        SharedPreferences sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void signin(){
        Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}