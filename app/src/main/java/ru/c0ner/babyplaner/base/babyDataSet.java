package ru.c0ner.babyplaner.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import ru.c0ner.babyplaner.Fragments.babyItemBase;
import ru.c0ner.babyplaner.Fragments.babyItemBudjet;
import ru.c0ner.babyplaner.Fragments.dialogDataReturn;
import ru.c0ner.babyplaner.R;

/**
 * Created by d.ivaka on 16.03.2018.
 */

public class babyDataSet {

    private static babyDataSet mbabyDataSet;
    private Context mContext;
    public ArrayList mBudjetList;
    public ArrayList mBudjetItems;
    public ArrayList mSumki;
    public ArrayList mSumkiItems;
    public babyDBHelper mDB;
    SQLiteDatabase db;

    public ArrayList<babyItemBase> getSumkiItems(int parent) {
        ArrayList result = new ArrayList<>();
        babyItemBase bm;
        for (int i = 0; i < mSumkiItems.size(); i++) {
            bm = (babyItemBase) mSumkiItems.get(i);
            if (bm.mParent == parent) {
                result.add(bm);
            }

        }

        return result;
    }

    public void setSumkiItems() {

        String[] grp_ID = new String[]{((Integer) 2).toString()};
        Cursor c = db.query("baby_items", null, "Group_ID = ?", grp_ID, null, null, null);
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("ID");
            int title = c.getColumnIndex("title");
            int parent_id = c.getColumnIndex("parent_ID");
            int kom_vo = c.getColumnIndex("kol_vo");
            //int emailColIndex = c.getColumnIndex("email");

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                babyItemBase m = new babyItemBase(c.getString(title));
                m.setParent(c.getInt(parent_id));

                mSumkiItems.add(m);
                Log.d("DB-3",
                        "Title  = " + m.getTitle().toString() + "ID = " + m.getParent());
            } while (c.moveToNext());
        } else
            Log.d("DB", "0 rows");
        c.close();


    }

    private babyDataSet(Context context) {
        mSumki = new ArrayList<babyItemBase>();
        mSumkiItems = new ArrayList<babyItemBase>();
        mBudjetItems = new ArrayList<babyItemBudjet>();
        mBudjetList = new ArrayList<babyItemBase>();
        mDB = new babyDBHelper(context);
        db = mDB.getReadableDatabase();

    }

    public static babyDataSet get(Context context) {
        if (mbabyDataSet == null) {
            mbabyDataSet = new babyDataSet(context);
            // this.mContext = context;
            return mbabyDataSet;
        }
        return mbabyDataSet;
    }

    public void setBudjetItemsList() {
        //  ArrayList  mB = new ArrayList<babyItemBudjet> ();
        /*
        for (int i = 0; i < list.length; i++) {
            babyItemBase m = new babyItemBudjet(list[i]);
            m.setParent(parent);
            mBudjetItems.add(m);
            //mBudjetItems.add(setBudjetItemsList(mitemlist_budjet[i],i));
        }
        */

        String[] grp_ID = new String[]{((Integer) 1).toString()};
        Cursor c = db.query("baby_items", null, "Group_ID = ?", grp_ID, null, null, null);
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("ID");
            int title = c.getColumnIndex("title");
            int parent_id = c.getColumnIndex("parent_ID");
            int kom_vo = c.getColumnIndex("kol_vo");
            //int emailColIndex = c.getColumnIndex("email");

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                babyItemBudjet m = new babyItemBudjet(c.getString(title));
                m.setParent(c.getInt(parent_id));
                m.setKolvo(c.getInt(kom_vo));
                mBudjetItems.add(m);
                Log.d("DB-3",
                        "Title  = " + m.getTitle().toString() + "ID = " + m.getParent());
            } while (c.moveToNext());
        } else
            Log.d("DB", "0 rows");
        c.close();

    }

    public void setBudjetList(String[] list) {
    /*
        for (int i = 0; i < list.length; i++) {
            babyItemBase m = new babyItemBase(list[i]);
            m.mGroupID = i;
            mBudjetList.add(m);

        }
      */
        String[] grp_ID = new String[]{((Integer) 1).toString()};
        Cursor c = db.query("baby_list", null, "Group_ID = ?", grp_ID, null, null, null);
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("ID");
            int title = c.getColumnIndex("title");
            //int emailColIndex = c.getColumnIndex("email");

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                babyItemBase m = new babyItemBase(c.getString(title));
                m.mGroupID = c.getInt(id);
                mBudjetList.add(m);
                Log.d("DB-2",
                        "Title  = " + m.getTitle().toString() + "ID = " + m.mGroupID);

            } while (c.moveToNext());
        } else
            Log.d("DB", "0 rows");
        c.close();

    }

    public ArrayList getSumkiList() {
        return mSumki;
    }

    public void setSumkiList(String[] list) {

        String[] grp_ID = new String[]{((Integer) 2).toString()};
        Cursor c = db.query("baby_list", null, "Group_ID = ?", grp_ID, null, null, null);
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("ID");
            int title = c.getColumnIndex("title");
            //int emailColIndex = c.getColumnIndex("email");

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                babyItemBase m = new babyItemBase(c.getString(title));
                m.mGroupID = c.getInt(id);
                mSumki.add(m);
                Log.d("DB-2",
                        "Title  = " + m.getTitle().toString() + "ID = " + m.mGroupID);

            } while (c.moveToNext());
        } else
            Log.d("DB", "0 rows");
        c.close();

    }

    public ArrayList<babyItemBase> getBudjetList() {
        return mBudjetList;
    }

    public ArrayList<babyItemBase> getBudjetItemList(int parent) {
        ArrayList result = new ArrayList<>();
        babyItemBudjet bm;
        for (int i = 0; i < mBudjetItems.size(); i++) {
            bm = (babyItemBudjet) mBudjetItems.get(i);
            if (bm.mParent == parent) {
                result.add(bm);
            }

        }

        return result;
    }

    public void addBudjetList(String s) {

        babyItemBase m = new babyItemBase(s, -1, mBudjetList.size());
        int grp_ID = insertItem("baby_list", s, 1);
        m.mGroupID = grp_ID;
        mBudjetList.add(m);

    }

    public void addBudjetItem(String s, int parent) {

        babyItemBudjet m = new babyItemBudjet(s, parent, 0, 0, 1, 0);
        int grp_ID = insertItem("baby_items", s, 1,parent);
        mBudjetItems.add(m);
    }


    public void addSumkiList(String s) {
        babyItemBase m = new babyItemBase(s, -1, mSumki.size());
        int grp_ID = insertItem("baby_list", s, 2);
        m.mGroupID = grp_ID;
        mSumki.add(m);
    }

    public void addsumkiItems(String s, int parent) {
        babyItemBase m = new babyItemBase(s, parent);
        int grp_ID = insertItem("baby_items", s, 2,parent);
        mSumkiItems.add(m);

    }

    public void editBudjetList(dialogDataReturn s) {
        mBudjetList.set(s.getPosition(), new babyItemBase(s.getTitle()));
    }

    public void editSumkiList(dialogDataReturn s) {
        mBudjetItems.set(s.getPosition(), new babyItemBase(s.getTitle()));
    }

    public void editBudjetItem(dialogDataReturn s) {
        mSumki.set(s.getPosition(), new babyItemBase(s.getTitle()));
    }

    public void editSumkiItem(dialogDataReturn s) {

        mSumkiItems.set(s.getPosition(), new babyItemBase(s.getTitle()));
    }

    public int insertItem(String table_name, String str, int grp_ID) {
        ContentValues cv = new ContentValues();
        cv.put("title", str);
        cv.put("Group_ID", grp_ID);
        long result = db.insert(table_name, null, cv);
        Log.d("DB-Insert", "Title = " + str + "ID = " + result);
        return (int) result;
    }

    public int insertItem(String table_name, String str, int grp_ID, int parent) {
        ContentValues cv = new ContentValues();
        cv.put("title", str);
        cv.put("Group_ID", grp_ID);
        cv.put("parent_ID",parent);
        long result = db.insert(table_name, null, cv);
        Log.d("DB-Insert", "Title = " + str + "ID = " + result);
        return (int) result;
    }
}
