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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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


        Date fechaActual=new Date();

        if (Dia.isChecked()) {
            if (c.moveToFirst()) {
                do {
                    System.out.println(c.getString(6));
                    Date fecha =new Date();
                    String[] fechad=c.getString(2).split("/");
                    String[] hora=c.getString(3).split(":");
                    fecha.setDate(Integer.parseInt( fechad[1]));
                    fecha.setMonth(Integer.parseInt( fechad[0])-1);
                    fecha.setYear(Integer.parseInt( "1"+fechad[2]));
                    fecha.setHours(Integer.parseInt(hora[0]));
                    fecha.setMinutes(Integer.parseInt(hora[1]));
                    if (!c.getString(6).equals("ninguno")) {
                        String[] fechaDividida = c.getString(2).split("/");
                        String posicion = fechaDividida[1];
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaActual);

                        System.out.println("posicion es :" + posicion);
                        System.out.println("el dia es:"+fechaActual.getDate());

                        if (fechaActual.getDate() == Integer.parseInt(posicion)) {
                            System.out.println("entro aqui");

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
                        Date fecha =new Date();
                        String[] fechad=c.getString(2).split("/");
                        String[] hora=c.getString(3).split(":");
                        fecha.setDate(Integer.parseInt( fechad[1]));
                        fecha.setMonth(Integer.parseInt( fechad[0])-1);
                        fecha.setYear(Integer.parseInt( "1"+fechad[2]));
                        fecha.setHours(Integer.parseInt(hora[0]));
                        fecha.setMinutes(Integer.parseInt(hora[1]));
                        System.out.println(c.getString(6));
                        if (!c.getString(6).equals("ninguno")) {
                            String[] fechaDividida = c.getString(2).split("/");
                            String posicion = fechaDividida[1];
                            Calendar calendar = Calendar.getInstance();

                            calendar.setTime(fechaActual);

                            calendar.add(Calendar.DAY_OF_YEAR, -7);

                            System.out.println("posicion es :" + posicion);
                            if (fecha.getTime()<=fechaActual.getTime()  ) {
                                System.out.println("fafaffaf"+fecha);
                                System.out.println("fafafafaf"+ calendar.getTime().getTime());
                                if(fecha.getTime()>calendar.getTime().getTime()){

                                    listItems.add(new mLista(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));
                                }
                                }


                            System.out.println("Esta es la fecha de la base de datos: " + c.getString(2));
                        }

                    } while (c.moveToNext());
                }
            }
            if (seleccionar.getSelectedItem().toString().equals("Quincena")) {
                if (c.moveToFirst()) {
                    do {
                        Date fecha =new Date();
                        String[] fechad=c.getString(2).split("/");
                        String[] hora=c.getString(3).split(":");
                        fecha.setDate(Integer.parseInt( fechad[1]));
                        fecha.setMonth(Integer.parseInt( fechad[0])-1);
                        fecha.setYear(Integer.parseInt( "1"+fechad[2]));
                        fecha.setHours(Integer.parseInt(hora[0]));
                        fecha.setMinutes(Integer.parseInt(hora[1]));
                        System.out.println(c.getString(6));
                        if (!c.getString(6).equals("ninguno")) {
                            String[] fechaDividida = c.getString(2).split("/");
                            String posicion = fechaDividida[1];
                            Calendar calendar = Calendar.getInstance();

                            calendar.setTime(fechaActual);

                            calendar.add(Calendar.DAY_OF_YEAR, -15);

                            System.out.println("posicion es :" + posicion);
                            if (fecha.getTime()<=fechaActual.getTime()  ) {
                                System.out.println("fafaffaf"+fecha);
                                System.out.println("fafafafaf"+ calendar.getTime().getTime());
                                if(fecha.getTime()>calendar.getTime().getTime()){

                                    listItems.add(new mLista(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));
                                }
                            }

                        }

                    } while (c.moveToNext());
                }

            }
           if (seleccionar.getSelectedItem().toString().equals("Mes")) {
                if (c.moveToFirst()) {
                    do {
                        Date fecha =new Date();
                        String[] fechad=c.getString(2).split("/");
                        String[] hora=c.getString(3).split(":");
                        fecha.setDate(Integer.parseInt( fechad[1]));
                        fecha.setMonth(Integer.parseInt( fechad[0])-1);
                        fecha.setYear(Integer.parseInt( "1"+fechad[2]));
                        fecha.setHours(Integer.parseInt(hora[0]));
                        fecha.setMinutes(Integer.parseInt(hora[1]));
                        System.out.println(c.getString(6));
                        if (!c.getString(6).equals("ninguno")) {
                            String[] fechaDividida = c.getString(2).split("/");
                            String posicion = fechaDividida[1];
                            Calendar calendar = Calendar.getInstance();

                            calendar.setTime(fechaActual);

                            calendar.add(Calendar.DAY_OF_YEAR, -7);

                            System.out.println("posicion es :" + posicion);
                            if (fecha.getTime()<=fechaActual.getTime()  ) {
                                System.out.println("fafaffaf"+fecha);
                                System.out.println("fafafafaf"+ calendar.getTime().getTime());
                                if(fecha.getTime()>calendar.getTime().getTime()){

                                    listItems.add(new mLista(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));
                                }
                            }

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