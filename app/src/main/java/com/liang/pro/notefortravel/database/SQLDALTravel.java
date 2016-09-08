package com.liang.pro.notefortravel.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.liang.pro.notefortravel.database.base.SQLiteDALBase;

public class SQLDALTravel extends SQLiteDALBase{

    public SQLDALTravel(Context pContext) {
        super(pContext);
    }

    @Override
    protected Object FindModel(Cursor cursor) {
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase pDataBase) {

    }

    @Override
    public void onUpgrade() {

    }
}
