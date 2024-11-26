package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


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
            + "imagen TEXT,"
            + "imagen2 TEXT);";
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
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen, imagen2) " +
                "VALUES ('Introduccion a la COP16', 'Natalia Fajardo', 20240824, 'Colombia es reconocida como uno de los países con mayor biodiversidad del mundo. Ubicada en una posición estratégica en el trópico y con una geografía variada que incluye cordilleras, costas, llanuras, selvas y desiertos, este país suramericano alberga una asombrosa riqueza de especies y ecosistemas. Su diversidad biológica no solo es un motivo de orgullo nacional, sino también un recurso invaluable para la humanidad, ya que desempeña un papel crucial en el equilibrio ecológico global. El territorio colombiano se caracteriza por su heterogeneidad geográfica, climática y ecológica. La presencia de los Andes, que atraviesan el país en tres ramales principales, crea una variedad de microclimas y ecosistemas únicos . Además, Colombia tiene costas en el océano Pacífico y el mar Caribe, cada una con características biológicas distintas. La Amazonía, considerada el pulmón del mundo, cubre una parte significativa del país y es una fuente inagotable de biodiversidad.', 'prueba', 'tucan','tucan');");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen, imagen2) " +
                "VALUES ('Acuerdos de la COP16', 'Autor 2', 20240821, 'Este es el segundo artículo', 'prueba', 'flor','flor');");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen, imagen2) " +
                "VALUES ('Firmas de la COP16', 'Autor 3', 20241122, 'Este es el tercer artículo', 'prueba', 'flor','flor');");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen, imagen2) " +
                "VALUES ('Aves en Colombia', 'Autor 3', 20241122, 'Este es el tercer artículo', 'prueba','flor','flor');");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen, imagen2) " +
                "VALUES ('Fauna y flora como cuidarla', 'Autor 3', 20241122, 'Este es el tercer artículo', 'prueba','flor','flor');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS ARTICULO");
        db.execSQL(TABLE_ARTICULO);
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
                return false;
            }
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
