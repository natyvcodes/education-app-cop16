package com.grupo1.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "cop.db";
    private static final int VERSION = 1;

    public static final String TABLE_USUARIOS = "HOLA";
    public static final String TABLE_LECTURA = "HOLa";
    public static final String TABLE_ARTICULO = "CREATE TABLE ARTICULO ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nombre TEXT NOT NULL,"
            + "autor TEXT NOT NULL,"
            + "fecha INTEGER NOT NULL,"
            + "parrafo TEXT NOT NULL,"
            + "audio TEXT,"
            + "imagen TEXT);";
    public static final String TABLE_PLANTA = "Hola";
    public static final String TABLE_ANIMAL = "";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_ARTICULO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertarArticulo(int id, String nombre, String autor, int fecha, String parrafo, String audio, String imagen){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("id", id);
        valores.put("nombre", nombre);
        valores.put("autor", autor);
        valores.put("fecha",fecha);
        valores.put("parrafo", parrafo);
        valores.put("audio", audio);
        valores.put("imagen", imagen);

        long resultado = db.insert("Articulo", null, valores);

        if (resultado == -1) {
            Log.e("DB_ERROR", "Error al insertar el articulo.");
        } else {
            Log.d("DB_SUCCESS", "Articulo insertado correctamente con ID: " + resultado);
        }
        db.close();
    }
    public String mostrarParrafo(int idArticulo){
        SQLiteDatabase db = this.getReadableDatabase();
        String texto = "";

        Cursor cursor = db.rawQuery("SELECT parrafo FROM articulo WHERE id = ?",
                new String[]{String.valueOf(idArticulo)});

        if (cursor.moveToFirst()) {
            texto = cursor.getString(0); // Obtén el valor de la primera columna
        }

        cursor.close();
        db.close();
        return texto;
    }

}
