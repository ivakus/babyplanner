package ru.c0ner.babyplaner.Fragments;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by d.ivaka on 15.03.2018.
 */

public class babyStoradge {

    public  static String STORAGE_NAME = "babv_v1";
    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public babyStoradge(Context cntxt) {
        context = cntxt;
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    public  boolean addData( String name, String value ){
        if( settings == null ){
            return false;
        }
        editor.putString( name, value );
        editor.commit();
        return true;
    }
    public  boolean addData( String name, boolean value ){
        if( settings == null ){
            return false;
        }
        editor.putBoolean( name, value );
        editor.commit();
        return true;
    }

    public  boolean addData( String name, int value ){
        if( settings == null ){
            return false;
        }
        editor.putInt( name, value );
        editor.commit();
        return true;
    }
    public  boolean addData( String name, long value ){
        if( settings == null ){
            return false;
        }
        editor.putLong( name, value );
        editor.commit();
        return true;
    }
    public  String getDataString( String name ){
        if( settings == null ){
            return "";
        }
        return settings.getString( name, "" );
    }

    public  boolean getDataBoolean( String name ){
        if( settings == null ){
            return true;
        }
        return settings.getBoolean( name,true);
    }

    public  int getDataInt( String name ){
        if( settings == null ){
            return -1;
        }
        return settings.getInt( name,-1);
    }

    public  long getDataLong( String name ){
        if( settings == null ){
            return 0;
        }
        return settings.getLong( name,0);
    }
}
