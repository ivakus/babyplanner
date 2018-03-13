package ru.c0ner.babyplaner.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Budjet.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Budjet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Budjet extends babyFragment implements   AdapterView.OnItemClickListener {

    String[] names;
    ListView listView;
    ArrayAdapter adapter;
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
        adapter = new ArrayAdapter<>(v.getContext(), R.layout.list_view, R.id.list_view_item_text, names);
        listView.setOnItemClickListener(Budjet.this);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        return v;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Do something in response to the click
        String str = "Test";
        ArrayAdapter t = (ArrayAdapter) parent.getAdapter();
        str = t.getItem(position).toString();
        //Toast.makeText(v.getContext(), str, Toast.LENGTH_SHORT).show();
        mItemSelectListiner.ItemSelect( TAG,str , mitemlist[position]);
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.add(Menu.NONE, 0, Menu.NONE, "Добавить");
        //menu.add(Menu.NONE, 1, Menu.NONE, "Редактировать");
        //menu.add(Menu.NONE, 2, Menu.NONE, "Удалить");

        //Toast.makeText(this.getContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String str = "";
        str = adapter.getItem(info.position).toString();
        Toast.makeText(this.getContext(), str, Toast.LENGTH_SHORT).show();
       // adapter.remove( (Object) adapter.getItem(info.position));
       // this.adapter.notifyDataSetChanged();
        return super.onContextItemSelected(item);
    }
}
