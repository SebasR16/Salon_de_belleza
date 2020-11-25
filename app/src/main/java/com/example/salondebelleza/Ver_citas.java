package com.example.salondebelleza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import java.io.Serializable;
import java.util.ArrayList;

public class Ver_citas extends AppCompatActivity {

    private ListView lista;
    private adapterlista1 adaptador;
    private Button Editar,Eliminar,Pagar;
    private Context context;
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
            final SQLiteDatabase db=dbHelper.getWritableDatabase();
            ArrayList<mLista> listItems = new ArrayList<>();
            Cursor c = db.rawQuery("SELECT id, nombre,fecha,hora, telefono FROM citas ", null);
            if (c.moveToFirst()){
                do {
                    listItems.add(new mLista( c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
                } while(c.moveToNext());
            }
            c.close();
            db.close();


         return listItems;

        }
        private void mostrardialogo(final mLista value){
        AlertDialog.Builder builder = new AlertDialog.Builder(Ver_citas.this);
        LayoutInflater inflater = getLayoutInflater();
            System.out.println(value.getNombre());
        View view = inflater.inflate(R.layout.opciones,null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
            System.out.println("chingen a su madre");
       // Pagar = (Button) findViewById(R.id.pago);
            System.out.println("la tuya en vinagre");
           /* Button registrar= view.findViewById(R.id.registrar);
            registrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
*/       Button BtnEditar=view.findViewById(R.id.btnEditar);
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
                }
            });
            Button BtnBorrar=view.findViewById(R.id.btnBorrar);
            BtnBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 eliminar(value);
                    finish();
                    startActivity(getIntent());
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
        System.out.println("chingen a su madre");

        System.out.println("la tuya en vinagre");
        cantidad=view.findViewById(R.id.txtCantidad);
           Button registrar= view.findViewById(R.id.registrar);
        final RadioButton rbEfectivo= view.findViewById(R.id.radioefectivo);
        final RadioButton rbTarjeta=view.findViewById(R.id.radiotarjeta);
            registrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String n=cantidad.getText().toString();
                    System.out.println(n);
                        if (rbEfectivo.isChecked()){
                            db.execSQL("UPDATE "+"citas"+" SET tipoPago ='Efectivo', dinero=  '"+n+" 'WHERE id = "+"'"+value.getId()+"'");

                        }
                    if (rbTarjeta.isChecked()){
                        db.execSQL("UPDATE "+"citas"+" SET tipoPago ='Tarjeta', dinero=  '"+n+" 'WHERE id = "+"'"+value.getId()+"'");

                    }


                    dialog.dismiss();
                }
            });

    }

}