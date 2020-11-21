package com.example.salondebelleza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button nueva_cita,ver_citas,historialcitas,catalogo;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        nueva_cita = (Button) findViewById(R.id.btnNuevacita);
        ver_citas = (Button) findViewById(R.id.btnvercitas);
        historialcitas = (Button) findViewById(R.id.btnhistorial);
        nueva_cita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Agendar_cita.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });
        ver_citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Ver_citas.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });
        historialcitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), historial.class);
                startActivityForResult(intent, 0);
                finish();//j
            }
        });
    }
}