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

import ru.c0ner.babyplaner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SumkiItems extends babyFragment implements  AdapterView.OnItemClickListener {

    public void setItems(String[] items) {
        mItems = items;
    }

    String [] mItems;
   // babyAdapter mAdapter;
    ListView mListView;

    public int getCurent_Group_ID() {
        return mCurent_Group_ID;
    }

    public void setCurent_Group_ID(int curent_Group_ID) {
        mCurent_Group_ID = curent_Group_ID;
    }

    public int mCurent_Group_ID = 0;

    public void setTitle(String title) {
        mTitle = title;
    }

    String mTitle;
    public final static  String TAG = "SummkiitemsTAG";


    public SumkiItems() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.layout_sumkitem,container,false);
        Log.d("Baby","Фрагмент SumkiItem создался ");
        mListView = v.getRootView().findViewById(R.id.sumki1_listview);
        TextView tw = v.findViewById(R.id.main_sum_title);
        tw.setText(mTitle);
        //mAdapter = new babyAdapter(v.getContext(),mItems);
        mAdapter = new babyAdapter(v.getContext(),mItemList);
        mListView.setOnItemClickListener( SumkiItems.this );
        mListView.setAdapter(mAdapter);
        registerForContextMenu(mListView);
        return v;
    }

    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Do something in response to the click
        String str = "Test";
        // str = parent.getAdapter().getItem(position).toString();
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
                mAdapter.remove(info.position);
                this.mAdapter.notifyDataSetChanged();
                break;
            }
        }

        return super.onContextItemSelected(item);
    }
    public void dialogAdd(){
        DialogFragment addDialog = new babyAddDialog();
        ((babyAddDialog) addDialog).setTitle("Добавить Элемент");
        addDialog.show(getFragmentManager(),"Add_Sumkiitem");
    }
    public void addItem (String s)
    {

        Toast.makeText(this.getContext(),s, Toast.LENGTH_SHORT).show();
        //mAdapter.insert(s);
        super.addItem(s);
        this.mAdapter.notifyDataSetChanged();
    }

    public void dialogEdit(int position){
        DialogFragment addDialog = new babyAddDialog();
        ((babyAddDialog) addDialog).setTitle("Редактировать");
        ((babyAddDialog) addDialog).setItems(((babyItemBase) mAdapter.getItem(position)).getTitle().toString());
        ((babyAddDialog) addDialog).setItemPosition(position);
        addDialog.show(getFragmentManager(),"Edit_SumkiItem");

    }

    public void editItem (dialogDataReturn s){
        mAdapter.mItems.set(s.getPosition(),new babyItemBase(s.getTitle()));
        this.mAdapter.notifyDataSetChanged();
    }
    public String getTAG (){
        return  TAG;
    }
}
