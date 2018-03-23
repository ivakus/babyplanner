package ru.c0ner.babyplaner.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ru.c0ner.babyplaner.R;

/**
 * Created by d.ivaka on 23.03.2018.
 */

public class babyBudjetItem extends babyFragment implements View.OnClickListener {

    public final static String TAG = "BabyItemFragmentTAG";

    Button mBtn_save = null;
    TextView mPrice_plan,mPrice_real,mKolvo,mTitle;

    public babyItemBudjet mItem = null;

    public babyItemBudjet getItem() {
        return mItem;
    }

    public void setItem(babyItemBudjet item) {
        mItem = item;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.item_budjet_layout, container, false);
        mTitle = v.getRootView().findViewById(R.id.budjet_item_title);
        mKolvo = v.getRootView().findViewById(R.id.budjet_item_kolvo);
        mPrice_plan = v.getRootView().findViewById(R.id.budjet_item_plan);
        mPrice_real = v.getRootView().findViewById(R.id.budjet_item_real);
        mBtn_save = v.getRootView().findViewById(R.id.budjet_item_save);
        setView();
        return v;

    }

    private void setView() {
        mTitle.setText(mItem.getTitle().toString());
        mPrice_real.setText(((Integer)mItem.getPriceReal()).toString());
        mPrice_plan.setText(((Integer)mItem.mPrice_plan).toString());
        mKolvo.setText(((Integer)mItem.getKolvo()).toString());

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.settings_save)
        {

        }
    }

}
