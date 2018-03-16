package ru.c0ner.babyplaner.Fragments;

/**
 * Created by d.ivaka on 16.03.2018.
 */

public class dialogDataReturn {

    public String mTitle = "";
    public int mPosition = -1;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public dialogDataReturn(String title, int position) {
        mTitle = title;
        mPosition = position;
    }

    public int getPosition() {
        return mPosition;

    }

    public void setPosition(int position) {
        mPosition = position;
    }
}
