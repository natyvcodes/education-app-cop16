package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.grupo1.myapp.ui.register.RegisterActivity;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "cop.db";
    private static final int VERSION = 2;

    public static final String TABLE_USUARIOS = "CREATE TABLE USUARIO ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nombre TEXT NOT NULL,"
            + "email TEXT NOT NULL,"
            + "contrasena TEXT NOT NULL);";
    public static final String TABLE_LECTURA = "CREATE TABLE LECTURA ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT"
            + "idUsuario INTEGER NOT NULL,"
            + "idArticulo INTEGER NOT NULL,"
            + "FOREIGN KEY(idUsuario) REFERENCES usuario(id),"
            + "FOREIGN KEY(idArticulo) REFERENCES articulo(id),"
            + "leido INTEGER);";
    public static final String TABLE_ARTICULO = "CREATE TABLE ARTICULO ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nombre TEXT NOT NULL,"
            + "autor TEXT NOT NULL,"
            + "fecha INTEGER NOT NULL,"
            + "parrafo TEXT NOT NULL,"
            + "audio TEXT,"
            + "imagen TEXT);";
    public static final String TABLE_PLANTA = "CREATE TABLE PLANTA ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nom_comun TEXT,"
            + "nom_cientifico TEXT,"
            + "idArticulo INTEGER NOT NULL,"
            + "FOREIGN KEY(idArticulo) REFERENCES articulo(id),"
            + "familia TEXT,"
            + "imagen TEXT);";
    public static final String TABLE_ANIMAL = "CREATE TABLE PLANTA ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nom_comun TEXT,"
            + "nom_cientifico TEXT,"
            + "idArticulo INTEGER NOT NULL,"
            + "FOREIGN KEY(idArticulo) REFERENCES articulo(id),"
            + "familia TEXT,"
            + "imagen TEXT,"
            + "sonido TEXT);";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_ARTICULO);
        db.execSQL(TABLE_USUARIOS);
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }
    public boolean verificarEmailExistente(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("usuario", new String[] {"email"}, "email=?", new String[] {email}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }


    public boolean registrarUsuario(String nombre, String email, String contrasena) {
        if (verificarEmailExistente(email)) {
            return false;
        }

        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put("nombre", nombre);
            value.put("email", email);
            value.put("contrasena", contrasena);

            long result = db.insert("usuario", null, value);
            if (result == -1) {
                Log.e("UsuarioDBHelper", "Error al insertar el usuario");
                return false;
            }
            Log.e("BASEDEDATOS", "Insertado usuario");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

}
