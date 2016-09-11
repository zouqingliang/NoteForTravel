package com.liang.pro.notefortravel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.liang.pro.notefortravel.model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class AccountDB {
    private static AccountDB accountDB;
    private SQLiteDatabase sqLiteDatabase;

    private AccountDB(Context context) {
        SQLiteHelper dbHelper = new SQLiteHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public static AccountDB getInstance(Context pContext){
        if (accountDB == null){
            accountDB = new AccountDB(pContext);
        }
        return accountDB;
    }

    public void saveAccount(Account account){
        if (account != null){
            ContentValues values = new ContentValues();
            values.put("category", account.getCategory());
            values.put("money", account.getMoney());
            values.put("add_date",account.getAddDate());
            values.put("state", account.getState());


            sqLiteDatabase.insert("account",null,values);
        }
    }

    public List<Account> loadAccount(){
        List<Account> accountList = new ArrayList<Account>();

        Cursor cursor = sqLiteDatabase.query("account",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Account account = new Account();
                account.setId(cursor.getInt(cursor.getColumnIndex("id")));
                account.setCategory(cursor.getString(cursor.getColumnIndex("category")));
                account.setMoney(cursor.getFloat(cursor.getColumnIndex("money")));
                account.setAddDate(cursor.getString(cursor.getColumnIndex("add_date")));
                account.setState(cursor.getInt(cursor.getColumnIndex("state")));
                accountList.add(account);
            }while (cursor.moveToNext());
        }
        if (cursor != null){
            cursor.close();
        }
        return accountList;
    }

    public void deleteAccount(int id){
        sqLiteDatabase.delete("account", "  id = " + id, null);
    }
}
