package com.example.salondebelleza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class historial extends AppCompatActivity {

    private ListView lista;
    private Adapterhistorial adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        lista = (ListView) findViewById(R.id.verhistorial);
        adaptador = new Adapterhistorial(this,GetArrayitem());
        lista.setAdapter(adaptador);
    }




    private ArrayList<mLista> GetArrayitem(){
        ArrayList<mLista> listItems = new ArrayList<>();
        listItems.add(new mLista("Felix","8/10/2020","8:00","150"));
        listItems.add(new mLista("sebas","10/9/2020","9:00","200"));
        listItems.add(new mLista("pol","26/11/2020","11:00","300"));
        return listItems;

    }
}