package ru.c0ner.babyplaner.base;

import android.content.Context;
import java.util.ArrayList;

import ru.c0ner.babyplaner.Fragments.babyItemBase;
import ru.c0ner.babyplaner.Fragments.babyItemBudjet;
import ru.c0ner.babyplaner.R;

/**
 * Created by d.ivaka on 16.03.2018.
 */

public class babyDataSet {

    private static  babyDataSet mbabyDataSet;
    private Context mContext;
    public ArrayList mBudjetList;
    public ArrayList mBudjetItems;
    public ArrayList mSumki;
    public ArrayList mSumkiItems;

    public ArrayList <babyItemBase> getSumkiItems(int parent) {
        ArrayList result = new ArrayList<>();
        babyItemBase bm ;
        for ( int i =0 ; i< mSumkiItems.size();i++) {
            bm = (babyItemBase) mSumkiItems.get(i);
            if (bm.mParent == parent) {result.add(bm);}

        }

        return result ;
    }

    public void setSumkiItems(String[] list,int parent) {
        for (int i = 0; i < list.length; i++) {
            babyItemBase m = new babyItemBase(list[i]);
            m.setParent(parent);
            mSumkiItems.add(m);
            //mBudjetItems.add(setBudjetItemsList(mitemlist_budjet[i],i));
        }
    }

    private babyDataSet (Context context){
        mSumki = new ArrayList<babyItemBase>();
        mSumkiItems = new ArrayList<babyItemBase>();
        mBudjetItems = new ArrayList<babyItemBase>();
        mBudjetList = new ArrayList<babyItemBase>();

    }
    public static babyDataSet get (Context context){
        if (mbabyDataSet == null)  {
                mbabyDataSet = new babyDataSet(context);
               // this.mContext = context;
                return mbabyDataSet;
        }
        return mbabyDataSet;
    }
    public  void setBudjetItemsList (String[] list,int parent){
      //  ArrayList  mB = new ArrayList<babyItemBudjet> ();
        for (int i = 0; i < list.length; i++) {
            babyItemBase m = new babyItemBudjet(list[i]);
            m.setParent(parent);
            mBudjetItems.add(m);
            //mBudjetItems.add(setBudjetItemsList(mitemlist_budjet[i],i));
        }

    }

    public void setBudjetList (String[] list) {

        for (int i = 0; i < list.length; i++) {
            babyItemBase m = new babyItemBase(list[i]);
            m.mGroupID = i;
            mBudjetList.add(m);

        }

    }

    public ArrayList getSumkiList() {
        return mSumki;
    }

    public void setSumkiList (String[] list) {


        for (int i = 0; i < list.length; i++) {
            babyItemBase m = new babyItemBase(list[i]);
            m.mGroupID = i;
            mSumki.add(m);
        }

    }
    public ArrayList<babyItemBase> getBudjetList (){
            return mBudjetList;
    }

    public ArrayList <babyItemBase> getBudjetItemList (int parent){
        ArrayList result = new ArrayList<>();
        babyItemBase bm ;
        for ( int i =0 ; i< mBudjetItems.size();i++) {
            bm = (babyItemBase) mBudjetItems.get(i);
            if (bm.mParent == parent) {result.add(bm);}

        }

        return result ;
    }
    public void addBudjetList (String s){
        babyItemBase m = new babyItemBase(s,-1,mBudjetList.size());
        mBudjetList.add(m);

    }
    public void addBudjetItem (String s , int parent )
    {
        babyItemBudjet m = new babyItemBudjet(s,parent,0,0,1,0);

        mBudjetItems.add(m);
    }


    public void addSumkiList (String s){
        babyItemBase m = new babyItemBase(s,-1,mSumki.size());
        mSumki.add(m);
    }
    public void addsumkiItems (String s, int parent){
        babyItemBase m = new babyItemBase(s,parent);
        mSumkiItems.add(m);
    }


}
