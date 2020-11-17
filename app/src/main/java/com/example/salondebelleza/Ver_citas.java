package com.example.salondebelleza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Ver_citas extends AppCompatActivity {

    private ListView lista;
    private adapterlista1 adaptador;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vercitas);

        lista = (ListView) findViewById(R.id.verlista);
         adaptador = new adapterlista1(this,GetArrayitem());
        lista.setAdapter(adaptador);



    }

        private ArrayList<mLista> GetArrayitem(){
        ArrayList<mLista> listItems = new ArrayList<>();
        listItems.add(new mLista("Felix","8/10/2020","8:00"));
            listItems.add(new mLista("sebas","10/9/2020","9:00"));
            listItems.add(new mLista("pol","26/11/2020","11:00"));
         return listItems;

        }


}