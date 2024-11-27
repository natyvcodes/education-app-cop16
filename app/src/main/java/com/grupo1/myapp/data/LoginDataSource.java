package com.grupo1.myapp.data;

import static androidx.core.content.ContextCompat.startActivity;

import com.grupo1.myapp.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LoginDataSource {

    private SQLiteOpenHelper dbHelper;
    private String nombre;
    private String correo;
    public LoginDataSource(SQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @SuppressLint("Range")
    public Result<LoggedInUser> login(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM usuario WHERE email = ? AND contrasena = ?";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, new String[]{username, password});
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") LoggedInUser loggedInUser = new LoggedInUser(
                        cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("nombre")));
                nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                correo = cursor.getString(cursor.getColumnIndex("email"));
                return new Result.Success<>(loggedInUser);
            } else {
                return new Result.Error(new IOException("Username or password incorrect"));
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Database error", e));
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }


    public void logout() {

    }


}
