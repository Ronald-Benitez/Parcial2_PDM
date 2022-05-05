package com.example.parcial2_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial2_pdm.db.dbEmpleados;
import com.example.parcial2_pdm.entidades.Empleado;

public class VerEmpleado extends AppCompatActivity {
    TextView tvNombre,tvEdad,tvCargo,tvSalario,tvIsss,tvAfp,tvRenta,salarioDescuento;
    Button regresar,delete;
    dbEmpleados db = new dbEmpleados(this);
    float isssDescuento,afpDescuento,renta,salarioNeto;
    Empleado empleado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_empleado);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        empleado = db.getEmpleado(id);

        tvNombre = findViewById(R.id.nombreApellido);
        tvEdad = findViewById(R.id.edadVer);
        tvCargo = findViewById(R.id.cargoVer);
        tvSalario = findViewById(R.id.salarioVer);
        tvIsss = findViewById(R.id.isss);
        tvAfp = findViewById(R.id.afp);
        tvRenta = findViewById(R.id.renta);
        salarioDescuento = findViewById(R.id.salarioDescuento);
        regresar = findViewById(R.id.regresar);
        delete = findViewById(R.id.delete);

        tvNombre.setText(empleado.getNombre()+" "+empleado.getApellido());
        tvEdad.setText(String.valueOf(empleado.getEdad()));
        tvCargo.setText(empleado.getCargo());
        tvSalario.setText(empleado.getSalario());

        isssDescuento = calcularIsss();
        afpDescuento = calcularAfp();

        salarioNeto = Float.parseFloat(empleado.getSalario()) - isssDescuento - afpDescuento;
        renta = calcularRenta();

        tvIsss.setText(String.valueOf(isssDescuento));
        tvAfp.setText(String.valueOf(afpDescuento));
        tvRenta.setText(String.valueOf(renta));
        salarioDescuento.setText(String.valueOf(salarioNeto-renta));

        regresar.setOnClickListener(v -> finish());

        delete.setOnClickListener(v -> {
            Boolean delete = db.deleteEmpleado(empleado.getId());
            if(delete){
                Toast.makeText(this, "Empleado eliminado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VerEmpleado.this, MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public float calcularIsss(){
        float descuento = 0;

        if(!empleado.getSalario().isEmpty()){
            float salario = Float.parseFloat(empleado.getSalario());
            descuento = salario * 0.03f;
        }

        return descuento;
    }

    public float calcularAfp(){
        float descuento = 0;

        if(!empleado.getSalario().isEmpty()){
            float salario = Float.parseFloat(empleado.getSalario());
            descuento = salario * 0.0725f;
        }

        return descuento;
    }

    public float calcularRenta(){
        float descuento = 0;

        if(!empleado.getSalario().isEmpty()){
            if(salarioNeto > 0.01f && salarioNeto < 472.00f){
                descuento = 0f;
            }else if(salarioNeto >= 472.01f && salarioNeto < 895.24f){
                descuento = salarioNeto - 472.00f;
                descuento = (descuento * 0.1f)+17.67f;
            }else if (salarioNeto >= 895.25f && salarioNeto < 2038.10f){
                descuento = salarioNeto - 895.24f;
                descuento = (descuento * 0.2f)+60.00f;
            }else if (salarioNeto >= 2038.11f){
                descuento = salarioNeto - 2038.10f;
                descuento = (descuento * 0.3f)+288.57f;
            }
        }

        return descuento;
    }
}