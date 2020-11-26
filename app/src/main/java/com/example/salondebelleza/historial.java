package com.example.salondebelleza;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class historial extends AppCompatActivity {

    private ListView lista;
    private Adapterhistorial adaptador;
    private RadioButton Dia,Periodo;
    private Spinner seleccionar;
    Calendar calendario = Calendar.getInstance();
    private Button aplicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        Dia = (RadioButton)findViewById(R.id.radioDia);
        Periodo = (RadioButton)findViewById(R.id.radioPeriodo);
        seleccionar = (Spinner) findViewById(R.id.filtros);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.periodos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seleccionar.setAdapter(adapter);
        seleccionar.setEnabled(false);
        aplicar =(Button)findViewById(R.id.aplicar);

        lista = (ListView) findViewById(R.id.verhistorial);
        adaptador = new Adapterhistorial(this,GetArrayitem());
        lista.setAdapter(adaptador);


        aplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lista.setAdapter(null);
                adaptador = new Adapterhistorial(getApplicationContext(),GetArrayitem());
                lista.setAdapter(adaptador);
            }
        });

    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioDia:
                if (checked) {
                    seleccionar.setEnabled(false);
                    break;
                }

            case R.id.radioPeriodo:
                if (checked) {
                    seleccionar.setEnabled(true);
                    break;
                }
        }
    }



        private ArrayList<mLista> GetArrayitem() {
        final helper dbHelper = new helper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<mLista> listItems = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT id, nombre,fecha,hora, telefono, dinero, tipoPago FROM citas ", null);

        int dia, mes, semana;
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH);
        semana = calendario.get(Calendar.WEEK_OF_YEAR);
        System.out.println("Esta es la fecha de hoy " + dia);
            System.out.println("FECHA   " + mes);
            System.out.println("La pinchi semana " +semana);
        if (Dia.isChecked()) {
            if (c.moveToFirst()) {
                do {
                    System.out.println(c.getString(6));
                    if (!c.getString(6).equals("ninguno")) {
                        String[] fechaDividida = c.getString(2).split("/");
                        String posicion = fechaDividida[1];
                        System.out.println("posicion es :" + posicion);
                        if (dia == Integer.parseInt(posicion)) {

                            listItems.add(new mLista(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));
                        }

                    }

                } while (c.moveToNext());
            }
        }
        if (Periodo.isChecked()) {

            if (seleccionar.getSelectedItem().toString().equals("Semana")) {
                if (c.moveToFirst()) {
                    do {
                        System.out.println(c.getString(6));
                        if (!c.getString(6).equals("ninguno")) {
                            String[] fechaDividida = c.getString(2).split("/");
                            String posicion = fechaDividida[1];
                            System.out.println("posicion es :" + posicion);
                            if (semana == Integer.parseInt(posicion)) {
                                listItems.add(new mLista(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));
                            }

                            System.out.println("Esta es la fecha de la base de datos: " + c.getString(2));
                        }

                    } while (c.moveToNext());
                }
            }
            if (seleccionar.getSelectedItem().toString().equals("Quincena")) {
                if (c.moveToFirst()) {
                    do {
                        System.out.println(c.getString(6));
                        if (!c.getString(6).equals("ninguno")) {
                            String[] fechaDividida = c.getString(2).split("/");
                            String posicion = fechaDividida[1];
                            System.out.println("posicion es :" + posicion);
                            if (Integer.parseInt(posicion)>=15) {
                                listItems.add(new mLista(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));
                            }

                            System.out.println("Esta es la fecha de la base de datos: " + c.getString(2));
                        }

                    } while (c.moveToNext());
                }

            }
            if (seleccionar.getSelectedItem().toString().equals("Mes")) {
                if (c.moveToFirst()) {
                    do {
                        System.out.println(c.getString(6));
                        if (!c.getString(6).equals("ninguno")) {
                            String[] fechaDividida = c.getString(2).split("/");
                            String posicion = fechaDividida[0];
                            System.out.println("posicion es :" + posicion);
                            if (mes + 1 == Integer.parseInt(posicion)) {
                                listItems.add(new mLista(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));
                            }

                            System.out.println("Esta es la fecha de la base de datos: " + c.getString(2));
                        }

                    } while (c.moveToNext());
                }
            }

        } else {

            if (c.moveToFirst()) {
                do {
                    System.out.println(c.getString(6));
                    if (!c.getString(6).equals("ninguno")) {
                        listItems.add(new mLista(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));
                    }

                } while (c.moveToNext());
            }
            c.close();
            db.close();
    }
        return listItems;

    }
}