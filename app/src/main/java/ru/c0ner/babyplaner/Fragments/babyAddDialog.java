package ru.c0ner.babyplaner.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.c0ner.babyplaner.R;

/**
 * Created by d.ivaka on 14.03.2018.
 */

public class babyAddDialog extends DialogFragment implements DialogInterface.OnClickListener {

    public int mItemPosition = -1;
    public String mItems="";
    public EditText et;
    public String mTitle;

    public  interface babyDialogreturnListener {
        public void babyDialogReturnItem (Object s);
    }
    babyDialogreturnListener mBabyDialogreturnListener ;

    public void setTitle(String title) {
        mTitle = title;
    }



    public void setItemPosition(int itemPosition) {
        mItemPosition = itemPosition ;
    }



    public void setItems(String items) {
        mItems=items;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mBabyDialogreturnListener = (babyDialogreturnListener) getActivity();
        }
        catch (ClassCastException e) {
            e.getMessage() ;
        }
    }



    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.add_dialog, null);
        et = v.findViewById(R.id.dialog_add_edit);
        TextView tw = v.findViewById(R.id.dialog_add_title);
        tw.setText(mTitle);
        et.setText(mItems);
        builder.setView(v);
        builder.setNegativeButton(R.string.str_otmena,this);

        if (mItemPosition == -1 ){builder.setPositiveButton(R.string.str_add,this);}
        else {builder.setPositiveButton(R.string.str_save,this);}

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == Dialog.BUTTON_POSITIVE) {
            dialogDataReturn res = new dialogDataReturn(et.getText().toString(),mItemPosition);
            //res.setTitle(et.getText().toString());
            //Toast.makeText(this.getContext(),mItems, Toast.LENGTH_SHORT).show();
            mBabyDialogreturnListener.babyDialogReturnItem(res);
        }
    }

}
