package com.example.parcial2_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial2_pdm.db.dbEmpleados;
import com.example.parcial2_pdm.entidades.Empleado;

public class RegistrarEmpleado extends AppCompatActivity {
    EditText txtNombre, txtApellido,txtEdad,txtCargo,txtSalario;
    Button ingresar;
    dbEmpleados db = new dbEmpleados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_empleado);

        txtNombre = findViewById(R.id.nombre);
        txtApellido = findViewById(R.id.apellido);
        txtEdad = findViewById(R.id.edad);
        txtCargo = findViewById(R.id.cargo);
        txtSalario = findViewById(R.id.salario);
        ingresar = findViewById(R.id.registrarEmpleado);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Empleado empleado = verificar();

                if(empleado != null){
                    long id = db.createEmpleado(empleado);

                    if(id>0){
                        Toast.makeText(RegistrarEmpleado.this, "Empleado registrado", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegistrarEmpleado.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegistrarEmpleado.this, "No debe dejar campos vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public Empleado verificar(){
        Boolean validar = true;

        String nombre="";
        String apellido="";
        String edad="";
        String cargo="";
        String salario="";

        try {
            nombre = txtNombre.getText().toString();
            apellido = txtApellido.getText().toString();
            edad = txtEdad.getText().toString();
            cargo = txtCargo.getText().toString();
            salario = txtSalario.getText().toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(nombre.isEmpty() || apellido.isEmpty() || edad.isEmpty() || cargo.isEmpty() || salario.isEmpty()){
            validar = false;
        }

        if(validar){
            Empleado empleado = new Empleado(0,nombre,apellido,cargo,Integer.parseInt(edad),salario);
            return empleado;
        }

        return null;
    }
}