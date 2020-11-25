package com.example.salondebelleza;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Agendar_cita extends AppCompatActivity {
    EditText fecha,hora, nombre, telefono;
    Calendar calendario = Calendar.getInstance();
    Calendar tiempo = Calendar.getInstance();

    Button btnCita,btncancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_cita);
        final helper dbHelper=new helper(this);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();
        fecha = findViewById(R.id.editfecha);
        hora =  findViewById(R.id.edithora);
         btnCita=(Button) findViewById(R.id.editguardar);
         nombre=(EditText) findViewById(R.id.editnombre);
         telefono=(EditText)  findViewById(R.id.editcel);
         btncancelar=(Button)findViewById(R.id.cancelar);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Agendar_cita.this, date, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(Agendar_cita.this, time, tiempo.get(Calendar.HOUR), tiempo.get(Calendar.MINUTE),true).show();
            }
        });
        btnCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=nombre.getText().toString();
                System.out.println("nombre"+ n);
                String f=fecha.getText().toString();
                String h=hora.getText().toString();
                String t=telefono.getText().toString();
                    if (nombre.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Ingrese el nombre de su cliente", Toast.LENGTH_SHORT).show();
                    }
                    if (fecha.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Ingrese la fecha de su cita", Toast.LENGTH_SHORT).show();
                    }
                    if (hora.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Ingrese la hora de su cita", Toast.LENGTH_SHORT).show();
                    }
                    if (telefono.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Ingrese el telefono de su cliente", Toast.LENGTH_SHORT).show();
                    }else {

                        boolean status = dbHelper.addCita(n, f, h, t);
                        if (status) {
                            Toast.makeText(getApplicationContext(), "Cita agendada correctamente", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(view.getContext(), Menu_principal.class);
                            startActivityForResult(intent, 0);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error al registrar", Toast.LENGTH_LONG).show();
                        }
                    }


            }
        });
        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Menu_principal.class);
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

        fecha.setText(sdf.format(calendario.getTime()));
    }
    private void actualizarInput2() {
        String formatoHora = "HH:mm"; //In which you need put here
        DateFormat horas = new SimpleDateFormat("HH:mm");
        hora.setText(horas.format(tiempo.getTime()));
    }

}