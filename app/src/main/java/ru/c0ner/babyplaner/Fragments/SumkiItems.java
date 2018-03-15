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
    babyAdapter mAdapter;
    ListView mListView;

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
        mAdapter = new babyAdapter(v.getContext(),mItems);
        mListView.setOnItemClickListener( SumkiItems.this );
        mListView.setAdapter(mAdapter);
        registerForContextMenu(mListView);
        return v;
    }

    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Do something in response to the click
        String str = "Test";
        str = parent.getAdapter().getItem(position).toString();
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
        addDialog.show(getFragmentManager(),"Add_Budjet");
    }
    public void addItem (String s)
    {

        Toast.makeText(this.getContext(),s, Toast.LENGTH_SHORT).show();
        mAdapter.insert(s);
        this.mAdapter.notifyDataSetChanged();
    }

    public void dialogEdit(int position){
        DialogFragment addDialog = new babyAddDialog();
        ((babyAddDialog) addDialog).setTitle("Редактировать");
        ((babyAddDialog) addDialog).setItems(mAdapter.getItem(position).toString());
        addDialog.show(getFragmentManager(),"Edit_Budjet");

    }
}
