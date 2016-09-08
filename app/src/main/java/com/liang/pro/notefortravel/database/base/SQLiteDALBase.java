package com.liang.pro.notefortravel.database.base;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public abstract class SQLiteDALBase implements SQLiteHelper.SQLiteDateTable {

    private Context mContext;
    private SQLiteDatabase sqLiteDatabase;

    public SQLiteDALBase(Context pContext) {
        this.mContext = pContext;
    }

    protected Context getContext(){
        return mContext;
    }

    public SQLiteDatabase getSQLiteDatabase(){
        if (sqLiteDatabase == null){
            sqLiteDatabase = SQLiteHelper.getInstance(mContext).getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    protected Boolean Delete (String p_TableName, String p_Condition){
        return getSQLiteDatabase().delete(p_TableName,"1=1" + p_Condition,null) >= 0;
    }

    protected List getList(String p_SqlText){
        Cursor _Cursor = ExecSql(p_SqlText);
        return CursorToList(_Cursor);
    }

    private List CursorToList(Cursor cursor) {
        List list = new ArrayList();
        while (cursor.moveToNext()){
            Object object = FindModel(cursor);
            list.add(object);
        }
        cursor.close();
        return list;
    }

    protected abstract Object FindModel(Cursor cursor);

    public Cursor ExecSql(String p_SqlText) {
        return getSQLiteDatabase().rawQuery(p_SqlText,null);
    }
}
