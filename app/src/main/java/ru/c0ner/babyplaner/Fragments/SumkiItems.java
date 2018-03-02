package ru.c0ner.babyplaner.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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
public class SumkiItems extends Fragment implements  AdapterView.OnItemClickListener {

    public void setItems(String[] items) {
        mItems = items;
    }

    String [] mItems;
    ArrayAdapter mAdapter;
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
        // mItems = getResources().getStringArray(R.array.budjet_malish_gigiena);
        mAdapter = new ArrayAdapter<>(v.getContext(),R.layout.list_view,R.id.list_view_item_text,mItems);
        mListView.setOnItemClickListener( SumkiItems.this );
        mListView.setAdapter(mAdapter);
        return v;
    }

    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Do something in response to the click
        String str = "Test";
        ArrayAdapter t = (ArrayAdapter) parent.getAdapter();
        str = t.getItem(position).toString();
        Toast.makeText(v.getContext(), str, Toast.LENGTH_SHORT).show();

    }

}
