package com.example.salondebelleza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.TimerTask;


public class catalogo extends AppCompatActivity {


    private ImageButton Cortes,Tintes,Uñas,Secado,Bases;
    private TextView titulo,descripcion,precio;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        Cortes = (ImageButton)findViewById(R.id.imgCortes);
        Tintes = (ImageButton)findViewById(R.id.imgTintes);
        Uñas = (ImageButton) findViewById(R.id.imgUñas);
        Secado = (ImageButton) findViewById(R.id.imgSecado);
        Bases = (ImageButton) findViewById(R.id.imgBases);

        final AlertDialog.Builder builder = new AlertDialog.Builder(catalogo.this);
        final LayoutInflater inflater = getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialogocat,null);
        builder.setView(v);
        ok = (Button) v.findViewById(R.id.ok);
        titulo = (TextView)v.findViewById(R.id.titledialog);
        descripcion = (TextView)v.findViewById(R.id.descripcion);
        precio = (TextView)v.findViewById(R.id.precio);

        final AlertDialog dialog = builder.create();


        Cortes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                titulo.setText("Cortes");
                descripcion.setText("Descripción: Se realizan todo tipo de cortes de cabello para todos los publicos");
                precio.setText("Precio: Desde 50$ a 100$");
                dialog.show();

            }
        });
        Tintes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo.setText("Tintes");
                descripcion.setText("Descripción: Se aplica una capa de tinte en el cabello de color a su elección");
                precio.setText("Precio: 150$");
                dialog.show();
            }
        });
        Uñas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo.setText("Uñas");
                descripcion.setText("Descripción: Se aplican uñas de diferentes estilos");
                precio.setText("Precio: 80$");
                dialog.show();
            }
        });

        Secado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo.setText("Secado");
                descripcion.setText("Descripción: Se aplica un secado de cabello total");
                precio.setText("Precio: 40$");
                dialog.show();
            }
        });
        Bases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo.setText("Bases");
                descripcion.setText("Descripción: Se aplican bases para su posterior tinte");
                precio.setText("Precio: 50$");
                dialog.show();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });

    }

}