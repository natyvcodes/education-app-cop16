package database;

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
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen) " +
                "VALUES ('Introduccion a la COP16', 'Autor 1', 20240824, 'Este es el primer artículo', NULL, NULL);");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen) " +
                "VALUES ('Acuerdos de la COP16', 'Autor 2', 20240821, 'Este es el segundo artículo', NULL, 'imagen2.png');");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen) " +
                "VALUES ('Firmas de la COP16', 'Autor 3', 20241122, 'Este es el tercer artículo', 'audio3.mp3', NULL);");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen) " +
                "VALUES ('Aves en Colombia', 'Autor 3', 20241122, 'Este es el tercer artículo', 'audio3.mp3', NULL);");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen) " +
                "VALUES ('Fauna y flora como cuidarla', 'Autor 3', 20241122, 'Este es el tercer artículo', 'audio3.mp3', NULL);");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen) " +
                "VALUES ('Natalia y Duvan', 'Autor 3', 20241122, 'Este es el tercer artículo', 'audio3.mp3', NULL);");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen) " +
                "VALUES ('Articulo de prueba', 'Autor 3', 20241122, 'Este es el tercer artículo', 'audio3.mp3', NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
