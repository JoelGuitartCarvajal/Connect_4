package com.example.connect_4.UTILS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {

    private final static String DBGame = "DBGame";
    private final static int VERSION = 1;
    private final static String sqlCreate = "CREATE TABLE Historial " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " nom TEXT, " +
            " data TEXT, " +
            " Midagraella INTEGER, " +
            "ControlTemps BOOLEAN, " +
            "TempsFinal TEXT," +
            "Resultat TEXT)";
    private static SQLite sqLiteInstance;

    private SQLite(Context context) {
        super(context, DBGame, null, VERSION);
    }

    public static SQLite getInstance(Context context) {
        if (sqLiteInstance == null) {
            sqLiteInstance = new SQLite(context);
        }
        return sqLiteInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int previous, int after) {
        db.execSQL("DROP TABLE IF EXISTS Historial");
        db.execSQL(sqlCreate);
    }

    public long register(ContentValues register) {
        SQLiteDatabase database = getWritableDatabase();
        return database.insert(Variables.historial, null, register);
    }

    public Cursor getDataFromDB() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + "Historial", null);
    }
}