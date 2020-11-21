package com.example.salondebelleza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Ver_citas extends AppCompatActivity {

    private ListView lista;
    private adapterlista1 adaptador;
    private Button Editar,Eliminar,Pagar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vercitas);
        lista = (ListView) findViewById(R.id.verLista);
         adaptador = new adapterlista1(this,GetArrayitem());
        lista.setAdapter(adaptador);

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.item,null);
        Pagar = v.findViewById(R.id.pago);
        //System.out.println("Que es esto: " + Pagar.getName);
        Pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Chingen a su madre fuera del metodo");
                mostrardialogo();
            }
        });

    }

        private ArrayList<mLista> GetArrayitem(){
        ArrayList<mLista> listItems = new ArrayList<>();
        listItems.add(new mLista("Felix","8/10/2020","8:00"));
            listItems.add(new mLista("sebas","10/9/2020","9:00"));
            listItems.add(new mLista("pol","26/11/2020","11:00"));
         return listItems;

        }
        private void mostrardialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Ver_citas.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogpago,null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
            System.out.println("chingen a su madre");
       // Pagar = (Button) findViewById(R.id.pago);

            Button registrar= view.findViewById(R.id.registrar);
            registrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

        }

}