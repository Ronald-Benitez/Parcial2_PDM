package com.example.parcial2_pdm.entidades;

import android.content.ContentValues;

public class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String cargo;
    private String salario;

    public Empleado(int id, String nombre, String apellido, String cargo, int edad, String salario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.edad = edad;
        this.salario = salario;
    }

    public Empleado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public ContentValues getContent(){
        ContentValues content = new ContentValues();

        content.put("nombre", nombre);
        content.put("apellido", apellido);
        content.put("edad", edad);
        content.put("cargo", cargo);
        content.put("salario", salario);

        return content;
    }
}
