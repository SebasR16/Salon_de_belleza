package com.example.salondebelleza;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.TimePickerDialog;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Calendar;

public class editcita extends AppCompatActivity {
EditText editNombre, editFecha, editHora, editCel;
Button btnEdit,btnCancelar;
    Calendar calendario = Calendar.getInstance();
    Calendar tiempo = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcita);
        final helper dbHelper=new helper(this);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();
       editNombre=(EditText) findViewById(R.id.editnombre);
       editFecha=(EditText) findViewById(R.id.editfecha);
        editHora=(EditText) findViewById(R.id.edithora);
        editCel=(EditText) findViewById(R.id.editcel);
       btnEdit=(Button) findViewById(R.id.editguardar);
       btnCancelar= (Button) findViewById(R.id.editcancelar);

        final int id= getIntent().getExtras().getInt("id");
       editNombre.setText(getIntent().getExtras().getString("nombre"));
       editCel.setText(getIntent().getExtras().getString("telefono"));
       editFecha.setText(getIntent().getExtras().getString("fecha"));
       editHora.setText(getIntent().getExtras().getString("hora"));
        editFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(editcita.this, date, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show();
            }

        });
        editHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(editcita.this, time, tiempo.get(Calendar.HOUR), tiempo.get(Calendar.MINUTE),true).show();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=editNombre.getText().toString();
                System.out.println("nombre"+ n);
                String f=editFecha.getText().toString();
                String h=editHora.getText().toString();
                String t=editCel.getText().toString();
                if (editNombre.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese el nombre de su cliente", Toast.LENGTH_SHORT).show();
                }
                if (editFecha.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese la fecha de su cita", Toast.LENGTH_SHORT).show();
                }
                if (editHora.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese la hora de su cita", Toast.LENGTH_SHORT).show();
                }
                if (editCel.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese el telefono de su cliente", Toast.LENGTH_SHORT).show();
                }else {
                    db.execSQL("UPDATE " + "citas" + " SET nombre = " + "'" + n + "', fecha=  '" + f + "',hora='" + h + "',telefono='" + t + "' WHERE id = " + "'" + id + "'");
                    Toast.makeText(getApplicationContext(), "Cita editada correctamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(editcita.this, Ver_citas.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Ver_citas.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }

    };
    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int horas, int minutos) {
            tiempo.set(Calendar.HOUR_OF_DAY, horas);
            tiempo.set(Calendar.MINUTE, minutos);
            actualizarInput2();
        }

    };
    private void actualizarInput() {
        String formatoDeFecha = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        editFecha.setText(sdf.format(calendario.getTime()));
    }
    private void actualizarInput2() {
        String formatoHora = "HH:mm"; //In which you need put here
        DateFormat horas = new SimpleDateFormat("HH:mm");
        editHora.setText(horas.format(tiempo.getTime()));
    }
}