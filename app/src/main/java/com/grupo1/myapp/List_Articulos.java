package com.grupo1.myapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


import java.util.ArrayList;
import java.util.List;


import database.DatabaseHelper;


public class List_Articulos extends AppCompatActivity {
    private final DatabaseHelper db = new DatabaseHelper(this);
    private BottomNavigationView bottomNavigationView;
    private TextView numArticulos;
    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_articulos);


        int[] colores = {getColor(R.color.Green), getColor(R.color.Red), getColor(R.color.BlueTwo), getColor(R.color.Yellow), getColor(R.color.Violet)};
        List<String> titulos = obtenerTitulos();
        LinearLayout linearLayout = findViewById(R.id.container);
        numArticulos = findViewById(R.id.numArticulos);
        numArticulos.setText("Articulos disponibles: " + titulos.size());
        int colorIndex = 0;
        for (String titulo : titulos) {
            Button button = new Button(this);
            Log.d("Titulo", titulo);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    200
            );
            layoutParams.setMargins(0, 20, 0, 20);
            button.setLayoutParams(layoutParams);
            button.setTransformationMethod(null); // Esto desactiva la transformación de texto a mayúsculas
            button.setText(titulo);
            button.setTextSize(16);

            Drawable buttonShape = getDrawable(R.drawable.button_shape).mutate();
            buttonShape.setTint(colores[colorIndex % colores.length]);
            //Aqui nos traemos la imagenes de cada articulo
            Drawable rightDrawable = getDrawable(R.drawable.prueba).mutate();
            button.setCompoundDrawablesWithIntrinsicBounds(null, null, rightDrawable, null);
            button.setBackground(buttonShape);
            button.setTextColor(Color.WHITE);




            button.setOnClickListener(v -> {
                Intent intent = new Intent(this, Articulos.class);
                ArrayList<String> articulo = obtenerInfoArticulos(titulo);
                intent.putExtra("articulo",articulo);
                String texto = articulo.get(4);
                String[] partes = texto.split("\\s+");
                StringBuilder texto1 = new StringBuilder();
                StringBuilder texto2 = new StringBuilder();
                StringBuilder texto3 = new StringBuilder();

                int longitudMinimaTexto1 = 120;
                int longitudMinimaTexto2 = 275;

                boolean primerPuntoEncontrado = false;

                for (String palabra : partes) {
                    if (!primerPuntoEncontrado) {
                        texto1.append(palabra).append(" ");
                        if (palabra.contains(".") && texto1.length() > longitudMinimaTexto1) {
                            System.out.println("Se encontró un . para texto1");
                            primerPuntoEncontrado = true;
                        }
                    } else if (texto2.length() <= longitudMinimaTexto2) {
                        texto2.append(palabra).append(" ");
                        if (palabra.contains(".") && texto2.length() > longitudMinimaTexto2) {
                            System.out.println("Se encontró un . para texto2");
                            // Aquí no necesitamos más control, pasamos al siguiente bloque.
                            break;
                        }
                    }
                }

                for (int i = texto1.length() + texto2.length(); i < texto.length(); i++) {
                    texto3.append(texto.charAt(i));
                }

                intent.putExtra("parrafo1", texto1.toString().trim());
                intent.putExtra("parrafo2", texto2.toString().trim());
                intent.putExtra("parrafo3", texto3.toString().trim());

                startActivity(intent);
            });

            linearLayout.addView(button);
            colorIndex++;
        }


        //Llamado al boton de navegacion
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Se selecciona el boton del activity en el que estamos: Articulos
        selectBottomNavItem(R.id.articulos);
        //Funcionalidad del boton: Cambiar de activity
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
    private List<String> obtenerTitulos(){
        List<String> titulos = new ArrayList<>();
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor cursor = data.rawQuery("SELECT nombre FROM ARTICULO", null);
        if (cursor.moveToFirst()) {
            do {
                titulos.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return titulos;
    }
    private ArrayList<String> obtenerInfoArticulos(String nomArticulo) {
        ArrayList<String> articulo = new ArrayList<>();
        SQLiteDatabase data = db.getReadableDatabase();
        Cursor cursor = data.rawQuery("SELECT * FROM ARTICULO where nombre =?", new String[]{nomArticulo});
        if (cursor.moveToFirst()) {
            int columnCount = cursor.getColumnCount();
            do {
                for (int i = 0; i < columnCount; i++) {
                    articulo.add(cursor.getString(i));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return articulo;
    }



}



