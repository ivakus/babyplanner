package ru.c0ner.babyplaner.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.c0ner.babyplaner.R;


public class Budjet extends babyFragment implements AdapterView.OnItemClickListener {


    ListView listView;
    // babyAdapter mAdapter;
    String mTitle;
    public final static String TAG = "BudjetTAG";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_budjet, container, false);

        listView = (ListView) v.findViewById(R.id.main_table);
        mAdapter = new babyAdapter(this.getContext(),mItemList);
        listView.setOnItemClickListener(Budjet.this);
        listView.setAdapter(mAdapter);
        registerForContextMenu(listView);
        return v;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Do something in response to the click
        String str = "Test";
        babyItemBase m = (babyItemBase) parent.getAdapter().getItem(position);
        str = m.getTitle().toString();
        int Group_ID = m.mGroupID;
        mItemSelectListiner.ItemSelect(TAG, str, Group_ID);
    }

    public void setTitle(String title) {
        mTitle = title;
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
                remove(info.position);
                //mAdapter.remove(info.position);
                this.mAdapter.notifyDataSetChanged();
                break;
            }
        }

        return super.onContextItemSelected(item);
    }
    public void dialogAdd(){
        DialogFragment addDialog = new babyAddDialog();
        ((babyAddDialog) addDialog).setTitle("Добавить Элемент");
        addDialog.show(getFragmentManager(),"Add_Budjet");
    }
    public void dialogEdit(int position){
        DialogFragment addDialog = new babyAddDialog();
        ((babyAddDialog) addDialog).setTitle("Редактировать");
        ((babyAddDialog) addDialog).setItems(mAdapter.getItem(position).toString());
        ((babyAddDialog) addDialog).setItemPosition(position);
        addDialog.show(getFragmentManager(),"Edit_Budjet");

    }
    public void addItem (String s)
    {

         //   Toast.makeText(this.getContext(), s, Toast.LENGTH_SHORT).show();
          //  super.addItem(s);
            //mAdapter.insert(s);
            this.mAdapter.notifyDataSetChanged();

    }
    public void editItem (dialogDataReturn s){
        super.editItem(s);
      //  mAdapter.mItems.set(s.getPosition(),new babyItemBase(s.getTitle()));
        this.mAdapter.notifyDataSetChanged();
    }
    public String getTAG (){
        return  TAG;
    }

}
