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


    private babyDataSet (Context context){


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
        ArrayList <babyItemBudjet> mB = new ArrayList<babyItemBudjet> ();
        for (int i = 0; i < list.length; i++) {
            babyItemBase m = new babyItemBudjet(list[i]);
            m.setParent(parent);
            mBudjetItems.add(m);
            //mBudjetItems.add(setBudjetItemsList(mitemlist_budjet[i],i));
        }
        mBudjetItems.add(mB);
    }
    public void setBudjetList (String[] list) {
        mBudjetItems = new ArrayList();
        mBudjetList = new ArrayList<babyItemBase>();
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

        mSumki = new ArrayList<babyItemBase>();
        for (int i = 0; i < list.length; i++) {
            babyItemBase m = new babyItemBase(list[i]);
            m.mGroupID = i;
            mSumki.add(m);
        }

    }
    public ArrayList<babyItemBase> getBudjetList (){
            return mBudjetList;
    }

    public ArrayList <babyItemBudjet> getBudjetItemList (int position){
        return (ArrayList)mBudjetItems.get(position);
    }
}
