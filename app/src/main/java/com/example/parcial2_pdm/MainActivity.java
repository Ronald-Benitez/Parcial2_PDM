package com.example.parcial2_pdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.parcial2_pdm.adaptadores.lista_empleados_adapter;
import com.example.parcial2_pdm.db.dbEmpleados;
import com.example.parcial2_pdm.db.dbHelper;
import com.example.parcial2_pdm.entidades.Empleado;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView lista;
    Button newEmpleado;
    dbEmpleados dbE = new dbEmpleados(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.lista);
        newEmpleado = findViewById(R.id.nuevo);
        lista.setLayoutManager(new LinearLayoutManager(this));

        createDB();
        ArrayList<Empleado> empleados = dbE.readEmpleados();
        lista_empleados_adapter adapter = new lista_empleados_adapter(empleados);
        lista.setAdapter(adapter);

        newEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrarEmpleado.class);
                startActivity(intent);
            }
        });
    }

    public void createDB(){
        dbHelper DBhelper = new dbHelper(MainActivity.this);
        SQLiteDatabase db = DBhelper.getWritableDatabase();
        if(db != null){
            Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Error la crear la base de datos", Toast.LENGTH_SHORT).show();

        }
    }
}