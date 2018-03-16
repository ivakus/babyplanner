package ru.c0ner.babyplaner.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import ru.c0ner.babyplaner.R;

/**
 * Created by d.ivaka on 13.03.2018.
 */

public class babyFragment extends Fragment implements  AdapterView.OnItemClickListener {


     ArrayList mItemList;

    public interface onItemSelectListiner  {
        public void ItemSelect (String fragmentTag, String s, int array_id );
    }
    public onItemSelectListiner mItemSelectListiner;

    public babyFragment() {
        super();
        mItemList = new ArrayList();
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

    public void onItemClick(AdapterView parent, View v, int position, long id) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.app_main,menu);


        //Toast.makeText(this.getContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //String str = "";
        //str = mAdapter.getItem(info.position).toString();
        //Toast.makeText(this.getContext(), str, Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
    public void remove (int position){
        mItemList.remove(position);
    }
    public void addItem(String s)
    {
        babyItemBase m = new babyItemBase(s);
        mItemList.add(m);
    }
    public void dialogAdd ()
    {

    }
    public void editItem (dialogDataReturn s){
        mItemList.set(s.getPosition(),new babyItemBase(s.getTitle()));
    }

    public void setItemList(ArrayList list) {
        mItemList = list;
    }
}
