package ru.c0ner.babyplaner.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ru.c0ner.babyplaner.R;

/**
 * Created by d.ivaka on 14.03.2018.
 */

public class babyAddDialog extends DialogFragment implements DialogInterface.OnClickListener {

    public  interface babyDialogreturnListener {
        public void babyDialogReturnItem (String s);
    }
    babyDialogreturnListener mBabyDialogreturnListener ;


    public String mItems;
    public EditText et;

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
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.add_dialog, null);
        et = v.findViewById(R.id.dialog_add_title);
        builder.setView(v);
        builder.setPositiveButton(R.string.str_dialog_add_title,this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        mItems = et.getText().toString();
        //Toast.makeText(this.getContext(),mItems, Toast.LENGTH_SHORT).show();
        mBabyDialogreturnListener.babyDialogReturnItem(mItems);
    }


}
