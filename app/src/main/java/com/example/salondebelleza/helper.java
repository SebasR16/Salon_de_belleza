package com.example.salondebelleza;
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
}
