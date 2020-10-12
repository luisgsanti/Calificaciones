package com.example.calificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CortesActivity extends AppCompatActivity {

    private TextView txtNombreMateria;
    Materia materia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cortes);

        Bundle extras = getIntent().getExtras();

        String posicion = extras.getString("posicion");

        MateriasDbHelper db = new MateriasDbHelper(getApplicationContext());

        materia = db.buscar(""+posicion);
        txtNombreMateria = (TextView) findViewById(R.id.txt_materia);
        txtNombreMateria.setText(materia.nombreMateria);

    }

    public void AbrirActividades(View view) {
        Intent intent = new Intent(this, ActividadesActivity.class);
        startActivity(intent);
    }
}