package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etCedula;
    private EditText etNombre;
    private EditText etTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCedula = (EditText) findViewById(R.id.etCedula);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
    }

    public void registrar (View view) {

        /*Instancia de AdminDB para poder hacer operaciones en la BD*/
        AdminBD adminbd = new AdminBD(this, "Persona", null, 1);

        /*Se abre BD para lectura y escritura*/
        SQLiteDatabase Persona = adminbd.getWritableDatabase();

        String cedula = etCedula.getText().toString();
        String nombre = etNombre.getText().toString();
        String telefono = etTelefono.getText().toString();

        if (!cedula.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty()) {

            ContentValues registro = new ContentValues();
            registro.put("cedula", cedula); /*A cedula llevele lo que han digitado en cedula*/
            registro.put("nombre", nombre);
            registro.put("telefono", telefono);

            /*Se abre BD*/
            /*Se hace vaciado con insert en la BD en la tabla usuario se carga con registro*/
            Persona.insert("usuario", null, registro);

            /*Cerrar BD*/
            Persona.close();

            etCedula.setText("");
            etNombre.setText("");
            etTelefono.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Favor ingresar todos los datos", Toast.LENGTH_LONG).show();
        }

    }

    public void consultar(View view) {

        AdminBD adminbd = new AdminBD(this, "Persona", null, 1);
        /*Abrir base de datos*/
        SQLiteDatabase BasedeDatos = adminbd.getWritableDatabase();

        String cedula = etCedula.getText().toString();

        if (!cedula.isEmpty()) {
            /*Variable que tendra registros*/
            Cursor fila = BasedeDatos.rawQuery("Select nombre, telefono from usuario where cedula ="+cedula, null);

            if (fila.moveToFirst()) {
                etNombre.setText(fila.getString(0));
                etTelefono.setText(fila.getString(1));
                BasedeDatos.close();
            }
            else {
                Toast.makeText(this, "No se encontro el usuario con esa cedula", Toast.LENGTH_LONG).show();
            }
        }
    }
}