package com.liang.pro.notefortravel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.liang.pro.notefortravel.model.Travel;
import com.liang.pro.notefortravel.utils.DateTools;

import java.util.ArrayList;
import java.util.List;

public class TravelDB {

    private static TravelDB travelDB;
    private SQLiteDatabase sqLiteDatabase;

    private TravelDB(Context context) {
        SQLiteHelper dbHelper = new SQLiteHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public static TravelDB getInstance(Context pContext){
        if (travelDB == null){
            travelDB = new TravelDB(pContext);
        }
        return travelDB;
    }

    public void saveTravel(Travel travel){
        if (travel != null){
            ContentValues values = new ContentValues();
            values.put("title",travel.getTitle());
            values.put("content",travel.getContent());
            values.put("write_time", DateTools.getWriteDate());
            values.put("travel_time",travel.getTravel_time().toString());
            values.put("destination",travel.getDestination());
            values.put("img_path",travel.getImg_path());

            sqLiteDatabase.insert("travel",null,values);
        }
    }

    public List<Travel> loadTravel(){
        List<Travel> travelList = new ArrayList<Travel>();

        Cursor cursor = sqLiteDatabase.query("travel",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Travel travel = new Travel();
                travel.setId(cursor.getInt(cursor.getColumnIndex("id")));
                travel.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                travel.setContent(cursor.getString(cursor.getColumnIndex("content")));
                travel.setWrite_time(cursor.getString(cursor.getColumnIndex("write_time")));
                travel.setTravel_time(cursor.getString(cursor.getColumnIndex("travel_time")));
                travel.setDestination(cursor.getString(cursor.getColumnIndex("destination")));
                travel.setImg_path(cursor.getString(cursor.getColumnIndex("img_path")));
                travelList.add(travel);
            }while (cursor.moveToNext());
        }
        if (cursor != null){
            cursor.close();
        }
        return travelList;
    }

    public void deleteTravel(int id){
        sqLiteDatabase.delete("travel", "  id = " + id, null);
    }
}
