package com.liang.pro.notefortravel.database.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.liang.pro.notefortravel.utils.Reflection;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper{

    private static SQLiteDatabaseConfig sqLiteDatabaseConfig;
    private static SQLiteHelper INSTENCE;
    private Context mContext;
    private Reflection mReflection;

    public SQLiteHelper(Context context) {
        super(context, sqLiteDatabaseConfig.getDatabaseName(), null, sqLiteDatabaseConfig.getVersion());
        mContext = context;
    }

    public static SQLiteHelper getInstance(Context pContext){
        if (INSTENCE == null){
            sqLiteDatabaseConfig = SQLiteDatabaseConfig.getInstance(pContext);
            INSTENCE = new SQLiteHelper(pContext);
        }
        return INSTENCE;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        ArrayList<String> arrayList = sqLiteDatabaseConfig.getTable();
        mReflection = new Reflection();
        for (int i = 0; i < arrayList.size(); i++){

            try {
                SQLiteDateTable _SQLiteDataTable = (SQLiteDateTable) mReflection.newInstance(
                        arrayList.get(i),
                        new Object[]{mContext},new Class[]{Context.class});
                _SQLiteDataTable.onCreate(sqLiteDatabase);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public interface SQLiteDateTable{
        public void onCreate(SQLiteDatabase pDataBase);
        public void onUpgrade();
    }
}
