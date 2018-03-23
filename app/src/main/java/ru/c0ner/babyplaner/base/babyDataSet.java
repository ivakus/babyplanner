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
    public ArrayList mCurentBudjetItemsList;
    public ArrayList mCurrentSumkiItemsList;
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
        mCurrentSumkiItemsList = result;
        return mCurrentSumkiItemsList;
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
                m.mID = c.getInt(id);
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
        mCurentBudjetItemsList = new ArrayList<babyItemBudjet>();
        mCurrentSumkiItemsList = new ArrayList<babyItemBase>();
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
                m.mID = c.getInt(id);
                mBudjetItems.add(m);
                Log.d("DB-3",
                        "Title  = " + m.getTitle().toString() + "ID = " + m.getParent());
            } while (c.moveToNext());
        } else
            Log.d("DB", "0 rows");
        c.close();

    }

    public void setBudjetList() {

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
                m.mID = c.getInt(id);
                mBudjetList.add(m);
                Log.d("DB-2",
                        "Title  = " + m.getTitle().toString() + "ID = " + m.mGroupID);

            } while (c.moveToNext());
        } else
            Log.d("DB", "0 rows");
        c.close();

    }

    public ArrayList <babyItemBase> getSumkiList() {
        return mSumki;
    }

    public void setSumkiList() {

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
                m.mID = c.getInt(id);
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

    public ArrayList<babyItemBudjet> getBudjetItemList(int parent) {
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
        updateItem("baby_list",s.item_ID,s.getTitle().toString());
        //queryItem("baby_list",s.item_ID);
        mBudjetList.set(s.getPosition(), queryItem("baby_list",s.item_ID) );
    }

    public void editSumkiList(dialogDataReturn s) {
        updateItem("baby_list",s.item_ID,s.getTitle().toString());
        mSumki.set(s.getPosition(), queryItem("baby_list",s.item_ID));
    }

    public void editBudjetItem(dialogDataReturn s) {

        updateItem("baby_items",s.item_ID,s.getTitle().toString());
        mBudjetItems.set(s.getPosition(), queryItemBudjet(s.item_ID));
    }

    public void editSumkiItem(dialogDataReturn s) {

        updateItem("baby_items",s.item_ID,s.getTitle().toString());
        mSumkiItems.set(s.getPosition(), queryItem("baby_items",s.item_ID) );
    }

    public void delBudjetList (int position,int ID) {
        removeList("baby_list",ID);
        mBudjetList.remove(position);
    }

    public void delSumkiList (int position,int ID) {
             removeList("baby_list",ID);
             mSumki.remove(position);
    }
    public  void delSumkiItemsList (int position, int ID){
        removeList("baby_items",ID);
        Object m = mCurrentSumkiItemsList.get(position);
        mCurrentSumkiItemsList.remove(position);
        mSumkiItems.remove(m);
    }
    public  void delBudjetItemsList (int position, int ID){
        removeList("baby_items",ID);
        Object m = mCurentBudjetItemsList.get(position);
        mCurentBudjetItemsList.remove(position);
        mBudjetItems.remove(m);
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

    public int updateItem (String table_name, int item_ID, String title)
    {
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        //cv.put("Group_ID", grp_ID);
        String[] grp_ID = new String[]{((Integer) item_ID).toString()};
        long result = db.update(table_name, cv,"ID = ?", grp_ID);
        Log.d("DB-Update", "Title = " + title + "ID = " + item_ID + "Table = "+table_name);
        return (int) 0;

    }
    public babyItemBase queryItem (String table_name, int item_ID){

        babyItemBase m = null;
        String[] _ID = new String[]{((Integer) item_ID).toString()};
        Cursor c = db.query(table_name, null, "ID = ?", _ID, null, null, null);
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("ID");
            int title = c.getColumnIndex("title");
            int grp_id = c.getColumnIndex("Group_ID");
            //int kom_vo = c.getColumnIndex("kol_vo");
            //int emailColIndex = c.getColumnIndex("email");
            m = new babyItemBase(c.getString(title));
            m.setParent(c.getInt(grp_id));
            //m.setKolvo(c.getInt(kom_vo));
            m.mID = c.getInt(id);
            m.mGroupID = c.getInt(grp_id);
            Log.d("DB-Query-Item",
                        "Title  = " + m.getTitle().toString() + "ID = " + m.getParent());
            }
        else
            Log.d("DB", "0 rows");
        c.close();
        return m;
    }

    public babyItemBudjet queryItemBudjet (int item_ID){

        babyItemBudjet m = null;
        String[] _ID = new String[]{((Integer) item_ID).toString()};
        Cursor c = db.query("baby_items", null, "ID = ?", _ID, null, null, null);
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int id = c.getColumnIndex("ID");
            int title = c.getColumnIndex("title");
            int grp_id = c.getColumnIndex("Group_ID");
            int price_plan = c.getColumnIndex("price_real");
            int price_real = c.getColumnIndex("price_plan");
            int parent_id = c.getColumnIndex("parent_ID");
            int kom_vo = c.getColumnIndex("kol_vo");
            //int emailColIndex = c.getColumnIndex("email");
            m = new babyItemBudjet( c.getString(title), c.getInt(parent_id),c.getInt(price_plan),c.getInt(price_real),c.getInt(kom_vo),0);

            m.setParent(c.getInt(grp_id));
            //m.setKolvo(c.getInt(kom_vo));
            m.mID = c.getInt(id);
            m.mGroupID = c.getInt(grp_id);
            Log.d("DB-Query-Item-Bu  ",
                    "Title  = " + m.getTitle().toString() + "I D = " + m.getParent());
        }
        else
            Log.d("DB-Query-Item-Bu", "0 rows");
        c.close();
        return m;
    }
    public void removeItem (String table_name, int ID)
    {
        String[] _ID = new String[]{((Integer) ID).toString()};
        long res = db.delete(table_name,"ID = ?",_ID);
        Log.d("DB-Delete-Item",
                "Title  = ID = " + ID + " Result = " +res);
    }
    public void removeList (String table_name, int ID)
    {
            String[] _ID = new String[]{((Integer) ID).toString()};
            db.delete(table_name,"ID = ?",_ID);
            long res = db.delete ("baby_items","parent_ID = ?",_ID);
        Log.d("DB-Delete-Item",
                "Title  = ID = " + ID + " Result = " +res);
    }
}
