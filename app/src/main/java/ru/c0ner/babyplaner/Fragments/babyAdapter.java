package ru.c0ner.babyplaner.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import ru.c0ner.babyplaner.R;

/**
 * Created by d.ivaka on 14.03.2018.
 */

public class babyAdapter extends BaseAdapter {


        Context context;
        ArrayList mItems;

        public static class ViewHolder {
            public TextView textView;
        }

        public babyAdapter (Context context, String[] list) {
            this.context = context;
            mItems = new ArrayList<>();
            Collections.addAll(mItems, list);

        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String str = (String) getItem(position);
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_view, parent, false);
                holder = new ViewHolder();
                holder.textView = (TextView)convertView.findViewById(R.id.list_view_item_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.textView.setText(str);
            return convertView;
        }

        public boolean remove (int position)
        {
            mItems.remove(position);
            return true;
        }


}
