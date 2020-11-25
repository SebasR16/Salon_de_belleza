package com.example.salondebelleza;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class helper extends SQLiteOpenHelper{
    private static final String USER_TABLE_CREATE = "CREATE TABLE usuarios(_id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, pass TEXT)";

    private static final String DB_NAME = "salon.sqlite";
    private static final int DB_VERSION = 1;
    public helper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
public boolean addCita(String n, String f, String h, String t){
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues c=new ContentValues();
    c.put("nombre",n);
    c.put("fecha",f);
    c.put("hora",h);
    c.put("telefono",t);
    c.put("dinero",0);
    c.put("tipoPago","ninguno");
    long result=db.insert("citas",null,c);
    if (result==-1){
        return false;
    } else {
        return true;
    }

}}
