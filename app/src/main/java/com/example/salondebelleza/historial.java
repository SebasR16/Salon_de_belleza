package com.example.salondebelleza;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        final helper dbHelper=new helper(this);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();
        ArrayList<mLista> listItems = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT id, nombre,fecha,hora, telefono, dinero, tipoPago FROM citas ", null);
        if (c.moveToFirst()){
            do {
                System.out.println(c.getString(6));
                if (!c.getString(6).equals("ninguno")){
                    listItems.add(new mLista( c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));
                }

            } while(c.moveToNext());
        }
        c.close();
        db.close();


        //listItems.add(new mLista("Felix","8/10/2020","8:00","150"));
        //listItems.add(new mLista("sebas","10/9/2020","9:00","200"));
        //listItems.add(new mLista("pol","26/11/2020","11:00","300"));
        return listItems;

    }
}