package com.example.calificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity {

    private MateriasDbHelper mDbHelper;
    private RecyclerView mReciclerView;
    private final LinkedList<String> mWordList = new LinkedList<>();
    ArrayList<String> listID;
    private wordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new MateriasDbHelper(this);
        cargarLista();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //displayDatabaseInfo();
        cargarLista();
        mReciclerView.smoothScrollToPosition(mWordList.size() + 1);
    }

    public void AgregarMateria(View view) {

        final Dialog dialog =new Dialog(MainActivity.this);
        //dialog.setTitle("Nueva Materia");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.agregar_materia);
        dialog.show();

        final EditText codigo = (EditText)dialog.findViewById(R.id.txt_CodigoMateria);
        final EditText nombreMateria = (EditText)dialog.findViewById(R.id.txt_NombreMateria);

        Button guardar = (Button)dialog.findViewById(R.id.btn_AgregarMateria);
        guardar.setText("agregar");

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put(CalificacionesContact.MateriaEntry.CODIGO_MATERIA, codigo.getText().toString().trim());
                    values.put(CalificacionesContact.MateriaEntry.NOMBRE_MATERIA, nombreMateria.getText().toString().trim());

                    db.insert(CalificacionesContact.MateriaEntry.TABLE_NAME, null, values);

                    Toast.makeText(getApplication(),"Materia añadida!", Toast.LENGTH_SHORT).show();

                    cargarLista();
                }catch (Exception e){
                    Toast.makeText(getApplication(),"Error al añadir materia",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

    }

    public void cargarLista(){
        try {
            mReciclerView = findViewById(R.id.reciclerView);
            MateriasDbHelper db = new MateriasDbHelper(getApplication());
            mWordList.clear();
            mWordList.addAll(db.llenarListRecycler());
            listID = db.listaID();
            mAdapter = new wordListAdapter(this, mWordList);
            mReciclerView.setAdapter(mAdapter);
            mReciclerView.setLayoutManager(new LinearLayoutManager(this));
            mReciclerView.smoothScrollToPosition(mWordList.size() + 1);

        } catch(Exception e){}
    }

    public void abrir(int position, String nombre){
        try{
            Intent intent = new Intent(MainActivity.this, CortesActivity.class);
            MateriasDbHelper db = new MateriasDbHelper(getApplication());
            listID = db.listaID();
            /*Toast.makeText(this,"Seleccionado: " + nombre, Toast.LENGTH_SHORT).show();*/
            String posicion = "" + listID.get((position));
            //intent.putExtra(EXTRA_MESSAGE,posicion);
            intent.putExtra("posicion",posicion);
            startActivity(intent);
        }catch (Exception e){
            //Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


}