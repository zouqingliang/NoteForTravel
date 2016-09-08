package com.liang.pro.notefortravel.database.base;

import android.content.Context;

import com.liang.pro.notefortravel.R;

import java.util.ArrayList;

public class SQLiteDatabaseConfig {

    private static final String DATABASE_NAME = "NoteForTravelDB";
    private static final int VERSION = 1;
    private static SQLiteDatabaseConfig INSTANCE;
    private static Context mContext;

    public SQLiteDatabaseConfig() {
    }

    public static SQLiteDatabaseConfig getInstance(Context pContext){
        if (INSTANCE == null){
            INSTANCE = new SQLiteDatabaseConfig();
            mContext = pContext;
        }
        return INSTANCE;
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static int getVersion() {
        return VERSION;
    }

    public ArrayList<String> getTable() {
        ArrayList<String> arrayList = new ArrayList<String>();
        String packagePath = mContext.getPackageName() + ".database.";
        String[] _SQLiteDALClassName = mContext.getResources().getStringArray(R.array.SQLiteDALClassName);
        for (int i = 0; i < _SQLiteDALClassName.length; i++){

            arrayList.add(packagePath + _SQLiteDALClassName[i]);
        }
        return arrayList;
    }
}
