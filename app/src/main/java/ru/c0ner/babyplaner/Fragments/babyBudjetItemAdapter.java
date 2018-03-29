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
 * Created by d.ivaka on 15.03.2018.
 */

public class babyBudjetItemAdapter extends babyAdapter {


    public static class ViewHolderBudjet {
        public TextView mTitle;
        public TextView mKolvo;
        public TextView mPrice; // стоимость Реальная
        public TextView mPrice_t; // стоимость прогназируемая

    }



   public babyBudjetItemAdapter (Context context, ArrayList list) {
        super (context,list);
    }
  public babyBudjetItemAdapter (Context context, String[] list) {
        super (context,list);
  }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        babyItemBudjet m = (babyItemBudjet) getItem(position);
        String str = (String) m.getTitle().toString();

        ViewHolderBudjet holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(super.context).inflate(R.layout.item_budjet, parent, false);
            holder = new ViewHolderBudjet();
            holder.mTitle = (TextView)convertView.findViewById(R.id.item_budjet_title);
            holder.mKolvo = (TextView)convertView.findViewById(R.id.item_budjet_kolvo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderBudjet) convertView.getTag();
        }
        holder.mTitle.setText(str);
        if (m.getPriceReal() > 0 ) {
            if (m.getPrice() > m.getPriceReal() ) {holder.mKolvo.setTextColor(Color.GREEN);}
            else {holder.mKolvo.setTextColor(Color.RED);}

            holder.mKolvo.setText(( (Integer) m.getPriceReal()).toString() + " руб.");

            }
        else {holder.mKolvo.setText(( (Integer) m.getPrice()).toString() + " руб.");}
        return convertView;
    }

    public boolean remove (int position)
    {
        mItems.remove(position);
        return true;
    }
    public boolean insert (String s){

        if (s.length() > 0 ) {
            babyItemBudjet item = new babyItemBudjet(s);
            mItems.add(item);
            return true;
        }
        return false;

    }


}
