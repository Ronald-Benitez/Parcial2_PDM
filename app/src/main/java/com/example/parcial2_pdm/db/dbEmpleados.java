package com.example.parcial2_pdm.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.parcial2_pdm.entidades.Empleado;

import java.util.ArrayList;

public class dbEmpleados extends dbHelper{
    Context context;
    public dbEmpleados(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long createEmpleado(Empleado empleado){
        long id = 0;

        try{
            dbHelper db = new dbHelper(context);
            SQLiteDatabase dbWrite = db.getWritableDatabase();

            ContentValues values = empleado.getContent();

            id = dbWrite.insert(TABLE_NAME, null, values);
        }catch (Exception e){
            e.printStackTrace();
        }

        return id;
    }

    public ArrayList<Empleado> readEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();

        try{
            dbHelper db = new dbHelper(context);
            SQLiteDatabase dbRead = db.getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = dbRead.rawQuery(query, null);

            if(cursor.moveToFirst()){
                do{
                    Empleado empleado = new Empleado();
                    empleado.setId(cursor.getInt(0));
                    empleado.setNombre(cursor.getString(1));
                    empleado.setApellido(cursor.getString(2));
                    empleado.setEdad(cursor.getInt(3));
                    empleado.setCargo(cursor.getString(4));
                    empleado.setSalario(cursor.getString(5));
                    Log.d("Empleado", empleado.toString());
                    empleados.add(empleado);
                }while(cursor.moveToNext());
                db.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return empleados;
    }

    public Empleado getEmpleado(int id){
        dbHelper db = new dbHelper(context);
        SQLiteDatabase dbRead = db.getReadableDatabase();

        Empleado empleado = null;
        Cursor cursor = dbRead.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = " + id, null);
        if(cursor.moveToFirst()){
            empleado = new Empleado();
            empleado.setId(cursor.getInt(0));
            empleado.setNombre(cursor.getString(1));
            empleado.setApellido(cursor.getString(2));
            empleado.setEdad(cursor.getInt(3));
            empleado.setCargo(cursor.getString(4));
            empleado.setSalario(cursor.getString(5));
        }
        cursor.close();
        return empleado;
    }

    public boolean deleteEmpleado(int id){
        boolean result = false;
        try{
            dbHelper db = new dbHelper(context);
            SQLiteDatabase dbWrite = db.getWritableDatabase();

            result = dbWrite.delete(TABLE_NAME, "id = " + id, null) > 0;
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
