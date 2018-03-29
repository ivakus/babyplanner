package ru.c0ner.babyplaner.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import ru.c0ner.babyplaner.R;



public class Setting extends babyFragment implements View.OnClickListener{

    final String STORADGE_NAME = "baby_v1";
    final String USER_NAME = "USER_NAME";
    final String FIRST_START = "FIRST_START";
    boolean isFirst = true;
    final String DAYS_RODOV = "DAYS_RODOV";
    final String DAYS_RODOV_LONG = "DAYS_RODOV_LONG";
    public babyStoradge mStor;
    String mUser_name = "Гость";
    int mDay = 180;
    long mDate_Rodov;
    Date mDate;
    SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
    EditText mUsername;
    TextView mDay_do_rodov;
    public final static String TAG = "SettingTAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_setting,container,false);
        mUsername = (EditText) v.getRootView().findViewById(R.id.setting_username);
        mDay_do_rodov = (TextView) v.getRootView().findViewById(R.id.setting_day_do_rodov);
        Button btn = (Button) v.getRootView().findViewById(R.id.settings_save);
        btn.setOnClickListener(this);
        mDay_do_rodov.setOnClickListener(this);
       // mStor = new babyStoradge (getContext());
        ReadData();
        return v;
    }

    public void ReadData (){

        isFirst = mStor.getDataBoolean(FIRST_START);
        if (!isFirst){
            mUser_name = mStor.getDataString(USER_NAME);
            mDay = mStor.getDataInt(DAYS_RODOV);
            mDate_Rodov = mStor.getDataLong(DAYS_RODOV_LONG);
        }
        mUsername.setText(mUser_name);
        // mDay_do_rodov.setText(""+mDay);
        //time = new Date();
        if (mDate_Rodov > 0) {
            mDate = new Date(mDate_Rodov);
        }
        else {
          mDate = new Date();
        }

        mDay_do_rodov.setText(" " + f.format(mDate));
    }
    public void SaveDada (){

        mStor.addData(FIRST_START,false);
        mStor.addData(USER_NAME,mUser_name);
        mStor.addData(DAYS_RODOV,mDay);
        mStor.addData(DAYS_RODOV_LONG,mDate.getTime());
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.settings_save)
        {


                mUser_name = mUsername.getText().toString();
              //  mDay = Integer.parseInt(mDay_do_rodov.getText().toString());
                SaveDada();
                mItemSelectListiner.ItemSelect(TAG, "Setting", 0);

        }
        if (v.getId() == R.id.setting_day_do_rodov) {
            Calendar c_ = new GregorianCalendar();
            int mm = mDate.getMonth();
            int yy = mDate.getYear()+1900;
            int dd = mDate.getDay();
            DatePickerDialog tpd = new DatePickerDialog(getContext(), myCallBack, yy,mm,dd);

            tpd.show();

        }
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            int myYear = year;
            int myMonth = monthOfYear;
            int myDay = dayOfMonth;
            //time.set(myDay,myMonth,myYear);
            Calendar c_ = new GregorianCalendar(myYear,myMonth,myDay);
            mDate.setTime(c_.getTimeInMillis());
            mDay_do_rodov.setText(" "+ f.format(mDate) );


           // time.set(myDay,myMonth,myYear);
          //mDay = time.getTime();
        }
    };
}
