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
                "VALUES ('Protección de áreas marinas', 'Duvan Mancilla', 20241031, 'Con gran ovación los asistentes a la plenaria de la COP16 celebraron la aprobación del acuerdo global para identificar y conservar áreas marinas de alta importancia ecológica en aguas internacionales luego de ocho años de arduo trabajo en las negociaciones.\n" +
                "\n" +
                "Este consenso marca un avance significativo en la implementación del Marco Global de Biodiversidad Kunming Montreal y fortalece la gobernanza global de los océanos . Esta decisión busca facilitar la conservación de la biodiversidad marina mientras se respetan los derechos y jurisdicciones de los Estados, según la Convención de las Naciones Unidas sobre el Derecho del Mar.Durante el anuncio, la presidenta de la COP16, Susana Muhamad, celebró el acuerdo como un primer gran paso en los objetivos de la conferencia: “El compromiso que hoy hemos asumido representa el espíritu de cooperación y responsabilidad que impulsa la COP16. Este acuerdo nos permitirá proteger áreas clave para el planeta, asegurando que los océanos, nuestros grandes reguladores climáticos y fuente de vida, tengan una defensa sólida y global”.\n" +
                "\n', 'audio_areas_protegidas', 'areas_marinas','areas_protegidas');");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen, imagen2) " +
                "VALUES ('Cinco Acuerdos de la COP16', 'Natalia Fajardo', 20241120, 'La Conferencia de las Partes sobre Diversidad Biológica (COP16) en Cali marcó un avance significativo hacia la implementación del Marco Mundial de Biodiversidad Kunming-Montreal. Sin embargo, su trabajo no ha concluido: la plenaria fue suspendida y se llevará a cabo una segunda sesión en el primer trimestre de 2025 para abordar los temas pendientes sobre financiamiento y completar el mandato de esta COP.\n" +
                "\nLa presidenta de la COP16 y ministra de Ambiente de Colombia, Susana Muhamad, destacó que esta conferencia se centró en la implementación. Entre los logros históricos de la conferencia clave sobresalen:\n" +
                "\n" +
                "1)Creación del Fondo de Cali – logro financiero: un mecanismo mundial para distribuir equitativamente recursos económicos a los Estados por el uso de su información genética, acuerdo histórico en favor de los países biodiversos.\n" +
                "\n"+"2)Creación del órgano subsidiario permanente del Artículo 8J para pueblos indígenas y comunidades locales, tras 26 años de discusiones. Histórico reconocimiento a los pueblos indígenas y comunidades locales. Con éste solo serían tres órganos subsidiarios del Convenio de Diversidad Biológica.\n" +
                "\n3)Acuerdo sobre áreas marinas en aguas internacionales, una decisión que reconoce la soberanía de los países y fortalece el papel de la ciencia para identificar puntos críticos de biodiversidad. Logro alcanzado luego de ocho años de negociaciones.\n" +
                "\n4)Interrelación entre las agendas de biodiversidad y cambio climático.\n" +
                "\n5)Reconocimiento de los pueblos afrodescendientes como custodios de la biodiversidad.\n" +
                "Estas iniciativas son esenciales para que los países puedan cumplir con los compromisos globales de protección de la biodiversidad.', 'exitos', 'grandes_acuerdos','medio_ambiental');");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen, imagen2) " +
                "VALUES ('Firmas de la COP16', 'Autor 3', 20241122, 'Este es el tercer artículo', 'prueba', 'flor','flor');");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen, imagen2) " +
                "VALUES ('Aves en Colombia', 'Autor 3', 20241122, 'Este es el tercer artículo', 'prueba','flor','flor');");
        db.execSQL("INSERT INTO ARTICULO (nombre, autor, fecha, parrafo, audio, imagen, imagen2) " +
                "VALUES ('Financiamiento Crisis Climatica', 'Duvan Mancilla', 20241118, 'En Bakú, donde se lleva a cabo la COP29 de Cambio Climático, la ministra de Ambiente de Colombia y presidenta de la COP16 de Biodiversidad, Susana Muhamad, destacó la urgencia de establecer un sistema financiero internacional que esté a la altura de la desafiante coyuntura climática, recalcando la importancia de invertir en adaptación, especialmente en marcos críticos, como inundaciones y sequías extremas, que tienen un costo anual de alrededor de 400 millones de dólares fuera del presupuesto establecido para responder a estas emergencias.\n" +
                "\nEn la presentación del Informe preliminar del Grupo de Expertos sobre Deuda, Naturaleza y Clima, establecido por los gobiernos de Colombia, Kenia, Francia y Alemania, en el marco de la COP19 se expusieron diferentes recomendaciones para poder hacer realidad la visión de reformar la estructura financiera global, destacando: la necesidad de reformar los sistemas financieros internacionales para integrar mejor los riesgos asociados al cambio climático y la biodiversidad; y los países en desarrollo como Colombia deberían recibir mayores apoyos financieros, incluidas las condiciones de financiamiento preferenciales, para poder avanzar en proyectos de transición verde y aumentar las inversiones en resiliencia climática, entre otros.\n" +
                "\n“Es fundamental establecer soluciones audaces, incluyendo la creación de un pacto global que permita redirigir parte de las deudas soberanas hacia inversiones climáticas estratégicas. En ese sentido, no podemos permitir tener una crisis de deuda con una crisis climática juntas. Eso sería catastrófico para todos y muy difícil de recuperar”, afirmó la ministra Susana Muhamad.\n" +
                "\n" +
                "En ese sentido, Muhamad recalcó el impacto negativo que conlleva la deuda externa con altos servicios de deuda sobre la capacidad que tienen los países en desarrollo para abordar los desafíos que impone el cambio climático y la conservación de la biodiversidad.\n" +
                "\n', 'financiamiento','financiero','animal_triste');");
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
            value.put("email", email.toLowerCase());
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
