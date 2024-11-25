package com.grupo1.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


public class Articulos extends AppCompatActivity {
    private TextView titulo;
    private TextView tvfecha;
    private BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_articulos);
        Intent intent = getIntent();
        ArrayList<String> articulo = intent.getStringArrayListExtra("articulo");
        titulo = findViewById(R.id.tv1);
        assert articulo != null;
        titulo.setText(articulo.get(1));
        tvfecha = findViewById(R.id.tvfecha);
        tvfecha.setText(articulo.get(3));

        //Menu
        selectBottomNavItem(R.id.articulos);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.home){
                    Intent intent = new Intent(Articulos.this, Principal.class);
                    startActivity(intent);
                    return true;
                }else if(id == R.id.articulos) {
                    Intent intent = new Intent(Articulos.this, List_Articulos.class);
                    startActivity(intent);
                    return true;
                }else if(id == R.id.setting){
                    return true;
                }
                return true;
            }
        });
    }
    private void selectBottomNavItem(int itemId) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(itemId); }
}