package com.grupo1.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DatabaseHelper;


public class Articulos extends AppCompatActivity {
    private final DatabaseHelper db = new DatabaseHelper(this);
    private TextView titulo, tvautor, tvfecha, tvparrafo1, tvparrafo2, tvparrafo3;
    private ImageView imageView,imageMedia;
    private RelativeLayout audioMedia;
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
        String fechaSinFormato = articulo.get(3);
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy/MM/dd");
        Date fecha = null;
        try {
            fecha = formatoEntrada.parse(fechaSinFormato);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String fechaFormateada = formatoSalida.format(fecha);
        tvfecha.setText(fechaFormateada);
        tvautor = findViewById(R.id.tvautor);
        tvautor.setText(articulo.get(2));
        audioMedia = findViewById(R.id.audiomedia);
        imageView = findViewById(R.id.imageView);
        imageMedia = findViewById(R.id.imageAudio);
        tvparrafo1 = findViewById(R.id.tvparrafo1);
        tvparrafo2 = findViewById(R.id.tvparrafo2);
        tvparrafo3 = findViewById(R.id.tvparrafo3);
        Bundle bundle = getIntent().getExtras();
        String parrafoInicial = bundle.getString("parrafo1");
        String parrafoMedio = bundle.getString("parrafo2");
        String parrafoFinal = bundle.getString("parrafo3");
        tvparrafo1.setText(parrafoInicial.trim());
        tvparrafo2.setText(parrafoMedio.trim());
        tvparrafo3.setText(parrafoFinal.trim());
        //logica para insertar imagen a la app
        String nomImage = articulo.get(6);
        String uri = "@drawable/" + nomImage;
        @SuppressLint("DiscouragedApi") int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable imageDra = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        imageView.setImageDrawable(imageDra);

        String nomImage2 = articulo.get(7);
        String uri2 = "@drawable/" + nomImage2;
        @SuppressLint("DiscouragedApi") int imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
        Drawable imageDra2 = ContextCompat.getDrawable(getApplicationContext(), imageResource2);
        imageMedia.setImageDrawable(imageDra2);
        Log.d("Imagen numero 2:", nomImage2);

        //reproducir audios
        Button button = new Button(this);

        Drawable icon = ContextCompat.getDrawable(this, R.drawable.play);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight()); // Ajustar tamaño del ícono
        button.setCompoundDrawables(icon, null, null, null); // Ícono a la izquierda


        button.setPadding(16, 16, 16, 16);
        button.setTextSize(12);
        button.setAllCaps(false);
        final MediaPlayer[] mediaPlayer = {null};
        final boolean[] isPlaying = {false};
        button.setOnClickListener(v -> {
            if (mediaPlayer[0] == null) {
                @SuppressLint("DiscouragedApi")
                int audioResId = getResources().getIdentifier(articulo.get(5), "raw", getPackageName());
                mediaPlayer[0] = MediaPlayer.create(this, audioResId);
                mediaPlayer[0].setOnCompletionListener(mp -> {
                    mediaPlayer[0].release();
                    mediaPlayer[0] = null;
                    isPlaying[0] = false;
                    button.setText("Reproducir"); // Cambiar el texto al completar
                });
            }

            if (isPlaying[0]) {
                mediaPlayer[0].pause();
                button.setText("Reproducir");
                isPlaying[0] = false;
            } else {
                mediaPlayer[0].start();
                button.setText("Pausar");
                isPlaying[0] = true;
            }
        });
        audioMedia.addView(button);

        //Menu
        selectBottomNavItem(R.id.articulos);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent intent = new Intent(Articulos.this, Principal.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.articulos) {
                    Intent intent = new Intent(Articulos.this, List_Articulos.class);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.setting) {
                    return true;
                }
                return true;
            }
        });

    }
    public void retroceder(View v){
        Intent intent = new Intent(this, List_Articulos.class);
        startActivity(intent);
    }

    private void selectBottomNavItem(int itemId) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(itemId);
    }
}