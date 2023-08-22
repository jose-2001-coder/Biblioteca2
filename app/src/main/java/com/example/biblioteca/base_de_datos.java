package com.example.biblioteca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class base_de_datos  extends SQLiteOpenHelper {


    public base_de_datos(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqldb = "CREATE TABLE IF NOT EXISTS usuarios(id_user int primary key,"+
                "nombre TEXT,"+
                "telefono TEXT,"+
                "rol TEXT,"+
                "direccion TEXT,"+
                "correo TEXT,"+
                "contrasena TEXT)";
        db.execSQL(sqldb);
        String prestamosdb = "CREATE TABLE IF NOT EXISTS prestamos(codigo int primary key,"+
                "serial TEXT,"+
                "descricion TEXT,"+
                "estado TEXT,"+
                "tipo TEXT)";
        db.execSQL(prestamosdb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Ecribir(String sqldb){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.execSQL(sqldb);
        sqLiteDatabase.close();
    }
    public void prestar(String prestamosdb){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.execSQL(prestamosdb);
        sqLiteDatabase.close();
    }

}
