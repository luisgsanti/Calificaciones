package com.example.calificaciones;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.calificaciones.CalificacionesContact;

import java.util.ArrayList;
import java.util.LinkedList;


public class MateriasDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shelther.db";
    private static final int DATABASE_VERSION = 1;

    public MateriasDbHelper(Context context) { super (context, DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_MATERIAS_TABLE = "CREATE TABLE " + CalificacionesContact.MateriaEntry.TABLE_NAME +" ("
                + CalificacionesContact.MateriaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CalificacionesContact.MateriaEntry.CODIGO_MATERIA + " TEXT NOT NULL, "
                + CalificacionesContact.MateriaEntry.NOMBRE_MATERIA + " TEXT NOT NULL, "
                + CalificacionesContact.MateriaEntry.DEFINITIVA_MATERIA + " DECIMAL NOT NULL DEFAULT 0.0);";
        db.execSQL(SQL_CREATE_MATERIAS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public LinkedList llenarListRecycler(){
        LinkedList<String> lista = new LinkedList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String aux = "SELECT * FROM materias";

        Cursor registro = database.rawQuery(aux,null);
        if(registro.moveToFirst()){
            do{
                lista.add("Codigo: "+registro.getString(1) + "\nMateria: " + registro.getString(2)
                        + "\nDefinitiva: " + registro.getString(3));
            }while(registro.moveToNext());
        }
        return lista;
    }

    public ArrayList listaID(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String q = "SELECT * FROM materias";
        Cursor registro = database.rawQuery(q, null);
        if(registro.moveToFirst()){
            do {
                lista.add(registro.getString(0));
            }while (registro.moveToNext());
        }
        return lista;
    }

    public Materia buscar(String codigo){
        Materia materia = new Materia();
        SQLiteDatabase database = this.getReadableDatabase();
        String q = "SELECT * FROM materias WHERE _ID='" +codigo+"'";
        Cursor registro = database.rawQuery(q, null);
        if(registro.moveToFirst()){
            materia.id= registro.getString(0);
            materia.codigo= registro.getString(1);
            materia.nombreMateria= registro.getString(2);
            materia.definitiva= Float.parseFloat(registro.getString(3));
        }
        return materia;
    }

}
