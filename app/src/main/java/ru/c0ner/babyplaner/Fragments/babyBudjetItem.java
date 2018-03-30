package ru.c0ner.babyplaner.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ru.c0ner.babyplaner.R;

import static android.text.InputType.TYPE_CLASS_NUMBER;

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
        mKolvo.setOnClickListener(this);
        mPrice_plan.setOnClickListener(this);
        mPrice_real.setOnClickListener(this);
        mBtn_save.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.budjet_item_kolvo)
        {
            DialogFragment addDialog = new babyAddDialog();
            // ((babyAddDialog) addDialog).setTitle("Редактировать");
            ((babyAddDialog) addDialog).setDialogAction(R.integer.dlg_EDIT);
            ((babyAddDialog) addDialog).setItems(""+mItem.getKolvo());
            ((babyAddDialog) addDialog).setItemPosition(R.id.budjet_item_kolvo);
            ((babyAddDialog) addDialog).setItem_ID(R.id.budjet_item_kolvo);
            ((babyAddDialog) addDialog).setType_metod(TYPE_CLASS_NUMBER);
            addDialog.show(getFragmentManager(), "BudjetITEM_EDIT");
        }
        if (v.getId() == R.id.budjet_item_plan)
        {
            DialogFragment addDialog = new babyAddDialog();
            // ((babyAddDialog) addDialog).setTitle("Редактировать");
            ((babyAddDialog) addDialog).setDialogAction(R.integer.dlg_EDIT);
            ((babyAddDialog) addDialog).setItems(""+mItem.getPrice());
            ((babyAddDialog) addDialog).setItemPosition(R.id.budjet_item_plan);
            ((babyAddDialog) addDialog).setItem_ID(R.id.budjet_item_plan);
            ((babyAddDialog) addDialog).setType_metod(TYPE_CLASS_NUMBER);
            addDialog.show(getFragmentManager(), "BudjetITEM_EDIT");
        }
        if (v.getId() == R.id.budjet_item_real)
        {
            DialogFragment addDialog = new babyAddDialog();
            // ((babyAddDialog) addDialog).setTitle("Редактировать");
            ((babyAddDialog) addDialog).setDialogAction(R.integer.dlg_EDIT);
            ((babyAddDialog) addDialog).setItems(""+mItem.getPriceReal());
            ((babyAddDialog) addDialog).setItemPosition(R.id.budjet_item_real);
            ((babyAddDialog) addDialog).setItem_ID(R.id.budjet_item_real);
            ((babyAddDialog) addDialog).setType_metod(TYPE_CLASS_NUMBER);
            addDialog.show(getFragmentManager(), "BudjetITEM_EDIT");
        }

        if (v.getId() == R.id.budjet_item_save){
            mItemList= new ArrayList<babyBudjetItem>();
            //mItem.setKolvo(Integer.parseInt(mKolvo.getText().toString()));
            mItem.setPrice(Integer.parseInt(mPrice_plan.getText().toString()));
            mItem.setPriceReal(Integer.parseInt(mPrice_real.getText().toString()));

            mItemSelectListiner.ItemSelect(TAG, mItem.getTitle().toString(),1);

        }

    }

    @Override
    public void dialogAdd() {
        //
    }

    @Override
    public void editItem(dialogDataReturn s) {
        // Integer.parseInt(mDay_do_rodov.getText().toString());
        if (s.item_ID == R.id.budjet_item_kolvo) {
            if ( s.getTitle().length()>0 ) {
                try {
                    mItem.setKolvo(Integer.parseInt(s.getTitle().toString()));
                } catch (NumberFormatException e) {
                    //Will Throw exception!
                    //do something! anything to handle the exception.
                }
                mKolvo.setText("" + mItem.mKolvo);
            }
        }

        if (s.item_ID == R.id.budjet_item_plan) {
            if ( s.getTitle().length()>0 ) {mPrice_plan.setText(s.getTitle().toString());}
        }
        if (s.item_ID == R.id.budjet_item_real) {
            if ( s.getTitle().length()>0 ) {mPrice_real.setText(s.getTitle().toString());}
        }


    }

    public String getTAG (){
        return  TAG;
    }
}
