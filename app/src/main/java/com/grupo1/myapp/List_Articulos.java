package com.grupo1.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class List_Articulos extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Button acuerdos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_articulos);
        acuerdos = findViewById(R.id.acuerdos);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        selectBottomNavItem(R.id.articulos);



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.home){
                    Intent intent = new Intent(List_Articulos.this, Principal.class);
                    startActivity(intent);
                }else if(id == R.id.articulos) {
                    Intent intent = new Intent(List_Articulos.this, List_Articulos.class);
                    startActivity(intent);
                }else if(id == R.id.setting){

                }
                return true;
            }
        });
    }
    private void selectBottomNavItem(int itemId) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(itemId);
    }
    public void acuerdosActivity(View view){
        Intent intent = new Intent(List_Articulos.this, Articulos.class);
        startActivity(intent);
    }

}



