package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app01sqlite.transacciones.Transacciones;

public class ActivityConsulta extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText id, nombres, apellidos, edad, correo, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        //Llamar a la conexion
        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        Button btnconsulta = (Button) findViewById(R.id.btnbuscar);
        Button btneliminar = (Button) findViewById(R.id.btneliminar);
        Button btnactualizar = (Button) findViewById(R.id.btnactualizar);

        id = (EditText) findViewById(R.id.txtcid);
        nombres = (EditText) findViewById(R.id.txtcnombres);
        apellidos = (EditText) findViewById(R.id.txtcapellidos);
        edad = (EditText) findViewById(R.id.txtcedad);
        correo = (EditText) findViewById(R.id.txtccorreo);
        direccion = (EditText) findViewById(R.id.txtcdireccion);

        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });
    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};
        String wherecond = Transacciones.id + "=?";
        db.delete(Transacciones.tablapersonas, wherecond, params);
        Toast.makeText(getApplicationContext(), "Dato eliminado",Toast.LENGTH_LONG).show();
        ClearScreen();
    }//Eliminar

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());

        db.update(Transacciones.tablapersonas, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato actualizado",Toast.LENGTH_LONG).show();
        ClearScreen();

    }//Actualizar

    private void Buscar() {
        /*Parametros de configuracion de sentencia SELECT*/
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};//parametro de la busquedad o el id
        String [] fields = {Transacciones.nombres,
                            Transacciones.apellidos,
                            Transacciones.correo,
                            Transacciones.edad,
                            Transacciones.direccion};
        String wherecond = Transacciones.id + "=?";

        try {
            Cursor cdata = db.query(Transacciones.tablapersonas, fields, wherecond, params, null, null, null);
            cdata.moveToFirst();

            nombres.setText(cdata.getString(0));
            apellidos.setText(cdata.getString(1));
            correo.setText(cdata.getString(2));
            edad.setText(cdata.getString(3));
            direccion.setText(cdata.getString(4));

            Toast.makeText(getApplicationContext(), "Consulta con exito",Toast.LENGTH_LONG).show();
        }
        catch (Exception ex) {
            ClearScreen();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado",Toast.LENGTH_LONG).show();
        }
    }//Buscar

    private void ClearScreen() {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");
    }//ClearScreen
}