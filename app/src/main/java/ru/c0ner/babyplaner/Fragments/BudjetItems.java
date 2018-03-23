package ru.c0ner.babyplaner.Fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.c0ner.babyplaner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BudjetItems extends babyFragment implements  AdapterView.OnItemClickListener {


    public void setTitle(String title) {
        mTitle = title;
    }


   // babyBudjetItemAdapter mAdapter;
    ListView mListView;
    //ArrayList mItemList;
    String mTitle;
   // public babyBudjetItemAdapter mAdapter;
    public final static String TAG = "BudjetitemsTAG";

    public int getCurent_Group_ID() {
        return mCurent_Group_ID;
    }

    public void setCurent_Group_ID(int curent_Group_ID) {
        mCurent_Group_ID = curent_Group_ID;
    }

    public int mCurent_Group_ID = 0;

    public BudjetItems() {
        super();
        mItemList = new ArrayList<babyItemBudjet>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_budjetitems, container, false);
        mListView = v.getRootView().findViewById(R.id.budjetitems_listview);
        TextView tw = v.findViewById(R.id.budjet_title);
        tw.setText(mTitle);
        mAdapter = new babyBudjetItemAdapter(v.getContext(),mItemList);
        mListView.setOnItemClickListener(BudjetItems.this);
        mListView.setAdapter(mAdapter);
        registerForContextMenu(mListView);
        return v;
    }

    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Do something in response to the click
        String str = "Test";
        //str = parent.getAdapter().getItem(position).toString();
        babyItemBase m = (babyItemBase) parent.getAdapter().getItem(position);
        str = m.getTitle().toString();
        Toast.makeText(v.getContext(), str, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.menu_add: {
                dialogAdd();
                break;
            }
            case R.id.menu_edit: {
                dialogEdit(info.position);
                break;
            }
            case R.id.menu_del: {
               // mAdapter.remove(info.position);
                dialogDelete(info.position);
                this.mAdapter.notifyDataSetChanged();
                break;
            }
        }

        return super.onContextItemSelected(item);
    }
    /*
    public void dialogAdd(){
            super.dialogAdd();
    }
    */
    public void addItem (String s)
    {
        //super.addItem(s);
        babyItemBudjet m = new babyItemBudjet(s);
        mItemList.add(m);
        Toast.makeText(this.getContext(),s, Toast.LENGTH_SHORT).show();
       // mAdapter.insert(s);
       // this.mAdapter.notifyDataSetChanged();
    }
    /*
    public void dialogEdit(int position){
        DialogFragment addDialog = new babyAddDialog();
        ((babyAddDialog) addDialog).setTitle("Редактировать");
        ((babyAddDialog) addDialog).setItems(((babyItemBase) mAdapter.getItem(position)).getTitle().toString());
        ((babyAddDialog) addDialog).setItemPosition(position);
        ((babyAddDialog) addDialog).setItem_ID (((babyItemBase) mAdapter.getItem(position)).mID);
        ((babyAddDialog) addDialog).setDialogAction(R.integer.dlg_EDIT);
        addDialog.show(getFragmentManager(),"Edit_Budjet");

    }
    */

    public void editItem (dialogDataReturn s){
        // super.editItem(s);
        mItemList.set(s.getPosition(),new babyItemBudjet(s.getTitle()));
        //mAdapter.mItems.set(s.getPosition(),new babyItemBase(s.getTitle()));
        this.mAdapter.notifyDataSetChanged();
    }
    public String getTAG (){
        return  TAG;
    }

    public void setItemList(ArrayList list) {
        mItemList = new ArrayList<babyItemBudjet>();
        mItemList = list;
    }
}
