package com.liang.pro.notefortravel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "NoteForTravelDB";
    private static final int VERSION = 1;

    private static final String CREATE_TRAVEL = "CREATE TABLE travel (" +
            "     id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "     title TEXT NOT NULL," +
            "     content TEXT NOT NULL, " +
            "     write_time CHAR NOT NULL," +
            "     travel_time CHAR NOT NULL," +
            "     destination TEXT NOT NULL," +
            "     img_path TEXT)";

    private static final String CREATE_ACCOUNT = "CREATE TABLE account (" +
            "     id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "     category TEXT NOT NULL," +
            "     money MONEY NOT NULL, " +
            "     add_date DATE NOT NULL, " +
            "     state INTEGER NOT NULL )";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TRAVEL);
        sqLiteDatabase.execSQL(CREATE_ACCOUNT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
