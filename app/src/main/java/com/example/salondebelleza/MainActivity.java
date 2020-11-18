package com.example.salondebelleza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText usuario;
    EditText pass;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button iniciar = (Button) findViewById(R.id.login);
        usuario=(EditText) findViewById(R.id.username);
        pass=(EditText) findViewById(R.id.password);
        helper dbHelper=new helper(this);
        final SQLiteDatabase db=dbHelper.getWritableDatabase();
        if(db !=null){
           db.execSQL("INSERT INTO usuarios (user, pass) VALUES ('Admin','1234')");
        }
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query=" select count(*) from usuarios where user ='"+usuario.getText().toString()+"' and pass='"+pass.getText().toString()+"'";
                System.out.println(query);
                cursor =db.rawQuery(query,null);
                cursor.moveToFirst();
                String count = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));
                System.out.println(count);


if(Integer.parseInt(count)>0){
    Intent intent = new Intent(v.getContext(), Menu_principal.class);
    startActivityForResult(intent, 0);

}else{
    System.out.println("no existe");
}





            }
        });
    }


}