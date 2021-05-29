package com.example.basededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*SQLiteOpenHelper es quien permite crear los metodos y demás para poder administrar la base de datos*/
public class AdminBD extends SQLiteOpenHelper {

    /*Constructor*/
    public AdminBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override /*Persona es en nombre de la BD*/
    public void onCreate(SQLiteDatabase Persona) {

        /*Creación de tabla de Base de Datos y sus atributos*/
        Persona.execSQL("create table usuario(cedula int primary key, nombre text, telefono int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
