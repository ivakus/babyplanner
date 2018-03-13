package ru.c0ner.babyplaner.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * Created by d.ivaka on 13.03.2018.
 */

public class babyFragment extends Fragment implements  AdapterView.OnItemClickListener {

    public interface onItemSelectListiner  {
        public void ItemSelect (String fragmentTag, String s, int array_id );
    }
    public onItemSelectListiner mItemSelectListiner;

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

    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Do something in response to the click
       String str = "Baby Base Fragment ";
       // ArrayAdapter t = (ArrayAdapter) parent.getAdapter();
        //str = t.getItem(position).toString();
        // Toast.makeText(v.getContext(), str, Toast.LENGTH_SHORT).show();
       // mItemSelectListiner.ItemSelect( str , mitemlist[position]);
    }

}
