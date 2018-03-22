package ru.c0ner.babyplaner.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by d.ivaka on 21.03.2018.
 */

public class babyDBHelper extends SQLiteOpenHelper {

    static String DB_Name = "Baby";
    static  int DB_Ver = 1;
    static String LOG_TAG = "Baby - DB Schema";
    public babyDBHelper(Context context) {
        super(context, DB_Name, null, DB_Ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String str ;
        str = "create table baby_list ( ID integer primary key autoincrement, title text, Group_ID integer);";
        Log.d(LOG_TAG, "--- onCreate database ---");
        Log.d(LOG_TAG, str);
        // создаем таблицу с полями
        db.execSQL(str);

        str = "create table baby_items ( ID integer primary key autoincrement, Group_ID integer, title text, parent_ID integer, price_plan integer,price_real integer, kol_vo integer);";
        db.execSQL(str);
        Log.d(LOG_TAG, str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

