package com.grupo1.myapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Principal extends AppCompatActivity {
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private TextView articulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);
        //Este es una prueba
        articulo = findViewById(R.id.parrafo);
        databaseHelper.insertarArticulo(1,"La guerra ecologica", "Judy",12032024,"Un texto random", "no hay", "No hay");
        String text = databaseHelper.mostrarParrafo(1);
        articulo.setText(text);
    }
}