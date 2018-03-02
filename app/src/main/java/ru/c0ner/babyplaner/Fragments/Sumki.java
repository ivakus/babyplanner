package ru.c0ner.babyplaner.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class Sumki extends Fragment implements  AdapterView.OnItemClickListener {

    public interface onItemSelectListiner  {
        public void ItemSelect (String s, int array_id );
    }
    onItemSelectListiner mItemSelectListiner;
    ListView mListView;
    String[] mItemsname;
    ArrayAdapter mArrayAdapter;
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
        mArrayAdapter = new ArrayAdapter<>(v.getContext(),R.layout.list_view,R.id.list_view_item_text,mItemsname);
        mListView.setOnItemClickListener( Sumki.this );
        mListView.setAdapter(mArrayAdapter);
        // adapter = new ArrayAdapter<>(v.getContext(), R.layout.list_view, R.id.list_view_item_text, names);

        return v;
    }


    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Do something in response to the click
        String str = "Test";
        ArrayAdapter t = (ArrayAdapter) parent.getAdapter();
        str = t.getItem(position).toString();
        // Toast.makeText(v.getContext(), str, Toast.LENGTH_SHORT).show();
        mItemSelectListiner.ItemSelect( str , mitemlist[position]);
    }
}
