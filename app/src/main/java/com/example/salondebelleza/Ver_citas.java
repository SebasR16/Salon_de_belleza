package com.example.salondebelleza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Ver_citas extends AppCompatActivity {

    private ListView lista;
    private adapterlista1 adaptador;
    private Button Editar,Eliminar,Pagar;
    private  ArrayList<mLista> datos;
    EditText cantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vercitas);
        lista = (ListView) findViewById(R.id.verLista);
        datos=GetArrayitem();
         adaptador = new adapterlista1(this,GetArrayitem());
        lista.setAdapter(adaptador);

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        lista.setClickable(true);
        View v = inflater.inflate(R.layout.item,null);
        //Pagar = v.findViewById(R.id.pago);
        //No entra el clickListener
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mostrardialogo(datos.get(i));

            }
        });

    }

        private ArrayList<mLista> GetArrayitem(){
            final helper dbHelper=new helper(this);
            Date fechaActual=new Date();

            final SQLiteDatabase db=dbHelper.getWritableDatabase();
            ArrayList<mLista> listItems = new ArrayList<>();
            Cursor c = db.rawQuery("SELECT id, nombre,fecha,hora, telefono FROM citas ", null);
            if (c.moveToFirst()){
                do {
                    Date fecha =new Date();
                    String[] fechad=c.getString(2).split("/");
                    String[] hora=c.getString(3).split(":");
                  fecha.setDate(Integer.parseInt( fechad[1])+1);
                  fecha.setMonth(Integer.parseInt( fechad[0])-1);
                    fecha.setYear(Integer.parseInt( "1"+fechad[2]));
                    fecha.setHours(Integer.parseInt(hora[0]));
                    fecha.setMinutes(Integer.parseInt(hora[1]));

                    if (fecha.getTime()>fechaActual.getTime()) {
                        System.out.println("fecha "+fecha.getTime());

                        listItems.add(new mLista(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
                    }

                } while(c.moveToNext());
            }
            c.close();
            db.close();


         return listItems;

        }
        private void mostrardialogo(final mLista value){
        final AlertDialog.Builder builder = new AlertDialog.Builder(Ver_citas.this);
        final AlertDialog.Builder builder2 = new AlertDialog.Builder(Ver_citas.this);

            LayoutInflater inflater = getLayoutInflater();
            System.out.println(value.getNombre());
        View view = inflater.inflate(R.layout.opciones,null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();

        dialog.show();
            System.out.println("chingen a su madre");
            System.out.println("la tuya en vinagre");
            System.out.println("Que gracioso felix gutierrez te wa madrear");

          Button BtnEditar=view.findViewById(R.id.btnEditar);
            BtnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Ver_citas.this, editcita.class);
                    intent.putExtra("id", value.getId());
                    intent.putExtra("nombre", value.getNombre());
                    intent.putExtra("fecha", value.getFecha());
                    intent.putExtra("hora", value.getHora());
                    intent.putExtra("telefono",value.getTelefono());
                    startActivity(intent);

                }
            });
            Button BtnPagar=view.findViewById(R.id.btnPagar);
            BtnPagar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mostrardialogoPago(value);
                    dialog.dismiss();
                }
            });
            Button BtnBorrar=view.findViewById(R.id.btnBorrar);
            BtnBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder2.setTitle("Nota");
                    builder2.setMessage("¿Seguro que desea eliminar esta cita?");
                    builder2.setCancelable(false);
                    builder2.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            Toast.makeText(getApplicationContext(), "Se ha eliminado la cita", Toast.LENGTH_SHORT).show();
                            eliminar(value);
                            finish();
                            startActivity(getIntent());
                        }
                    });
                    builder2.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            finish();
                        }
                    });
                    builder2.show();

                }
            });
    }

private  void  eliminar(final mLista value){
    final helper dbHelper=new helper(this);
    final SQLiteDatabase db=dbHelper.getWritableDatabase();
    db.execSQL("DELETE FROM citas WHERE id='"+value.getId()+"'");
}
    private void mostrardialogoPago(final mLista value){
        final helper dbHelper=new helper(this);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();
        AlertDialog.Builder builder = new AlertDialog.Builder(Ver_citas.this);
        LayoutInflater inflater = getLayoutInflater();
        System.out.println(value.getNombre());
        View view = inflater.inflate(R.layout.dialogpago,null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();

        dialog.show();
        cantidad=view.findViewById(R.id.txtCantidad);
           Button registrar= view.findViewById(R.id.registrar);
        final RadioButton rbEfectivo= view.findViewById(R.id.radioefectivo);
        final RadioButton rbTarjeta=view.findViewById(R.id.radiotarjeta);
            registrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String n=cantidad.getText().toString();
                    System.out.println(n);
                    if(cantidad.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Por favor ingrese el monto total de la cita", Toast.LENGTH_SHORT).show();

                    }
                    if (!rbEfectivo.isChecked() && !rbTarjeta.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Por favor seleccione un metodo de pago", Toast.LENGTH_SHORT).show();

                    }else{

                        if (rbEfectivo.isChecked()) {
                            db.execSQL("UPDATE " + "citas" + " SET tipoPago ='Efectivo', dinero=  '" + n + " 'WHERE id = " + "'" + value.getId() + "'");

                        }
                        if (rbTarjeta.isChecked()) {
                            db.execSQL("UPDATE " + "citas" + " SET tipoPago ='Tarjeta', dinero=  '" + n + " 'WHERE id = " + "'" + value.getId() + "'");

                        }
                        dialog.dismiss();
                    }
                }
            });

    }

}