package ru.c0ner.babyplaner.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class Sumki extends babyFragment implements  AdapterView.OnItemClickListener {
//
//    public interface onItemSelectListiner  {
//        public void ItemSelect (String s, int array_id );
//    }
//    onItemSelectListiner mItemSelectListiner;

    ListView mListView;
    String[] mItemsname;
    babyAdapter mAdapter;

    public final static String TAG = "SumkiTAG";
    public final static  int[] mitemlist ={
            R.array.sumki_1,
            R.array.sumki_2,
            R.array.sumki_3,
            R.array.sumki_4,
            R.array.sumki_5

    };

    public Sumki() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mItemSelectListiner = (onItemSelectListiner) getActivity();
        }
        catch (ClassCastException e) {
            e.getMessage() ;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_sumki,container,false);
        mListView = v.getRootView().findViewById(R.id.sumki_listview);
        mItemsname = getResources().getStringArray(R.array.sumki);
        mAdapter = new babyAdapter(v.getContext(),mItemsname);
        mListView.setOnItemClickListener( Sumki.this );
        mListView.setAdapter(mAdapter);
        registerForContextMenu(mListView);
        return v;
    }


    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Do something in response to the click
        String str = "Test";
        str = parent.getAdapter().getItem(position).toString();
        mItemSelectListiner.ItemSelect( TAG,str , mitemlist[position]);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String str = "";
        str = mAdapter.getItem(info.position).toString();
        // Toast.makeText(this.getContext(), str, Toast.LENGTH_SHORT).show();
        mAdapter.remove( info.position);
        this.mAdapter.notifyDataSetChanged();
        return super.onContextItemSelected(item);
    }
}
