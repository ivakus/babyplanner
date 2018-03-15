package ru.c0ner.babyplaner.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
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

import ru.c0ner.babyplaner.R;


public class Budjet extends babyFragment implements AdapterView.OnItemClickListener {

    String[] names;
    ListView listView;
    // ArrayAdapter adapter;
    babyAdapter mAdapter;
    String mTitle;
    public final static String TAG = "BudjetTAG";
    public final static int[] mitemlist = {
            R.array.budjet_1, R.array.budjet_2, R.array.budjet_3, R.array.budjet_4, R.array.budjet_5, R.array.budjet_6,
            R.array.budjet_7, R.array.budjet_8, R.array.budjet_9, R.array.budjet_10, R.array.budjet_11, R.array.budjet_12,
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_budjet, container, false);
        // Inflate the layout for this fragment
        names = getResources().getStringArray(R.array.budjet_items_name);
        listView = (ListView) v.findViewById(R.id.main_table);
        ///
        //adapter = new ArrayAdapter<>(v.getContext(), R.layout.list_view, R.id.list_view_item_text, names);
        mAdapter = new babyAdapter(this.getContext(), names);
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
        str = parent.getAdapter().getItem(position).toString();
        mItemSelectListiner.ItemSelect(TAG, str, mitemlist[position]);
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
        addDialog.show(getFragmentManager(),"Add_Budjet");
    }
    public void dialogEdit(int position){
        DialogFragment addDialog = new babyAddDialog();
        ((babyAddDialog) addDialog).setTitle("Редактировать");
        ((babyAddDialog) addDialog).setItems(mAdapter.getItem(position).toString());
        addDialog.show(getFragmentManager(),"Edit_Budjet");

    }
    public void addItem (String s)
    {

         //   Toast.makeText(this.getContext(), s, Toast.LENGTH_SHORT).show();
            mAdapter.insert(s);
            this.mAdapter.notifyDataSetChanged();

    }
}
