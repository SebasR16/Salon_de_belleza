package com.example.salondebelleza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editcita extends AppCompatActivity {
EditText editNombre, editFecha, editHora, editCel;
Button btnEdit;
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
        final int id= getIntent().getExtras().getInt("id");
       editNombre.setText(getIntent().getExtras().getString("nombre"));
       editCel.setText(getIntent().getExtras().getString("telefono"));
       editFecha.setText(getIntent().getExtras().getString("fecha"));
       editHora.setText(getIntent().getExtras().getString("hora"));
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=editNombre.getText().toString();
                System.out.println("nombre"+ n);
                String f=editFecha.getText().toString();
                String h=editHora.getText().toString();
                String t=editCel.getText().toString();


                db.execSQL("UPDATE "+"citas"+" SET nombre = "+"'"+n+"', fecha=  '"+f+ "',hora='"+h+"',telefono='"+t+"' WHERE id = "+"'"+id+"'");
                Intent intent=new Intent(editcita.this,Ver_citas.class);
                startActivity(intent);
            }
        });
    }
}