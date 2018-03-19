package ru.c0ner.babyplaner.Fragments;

import java.security.acl.Group;

/**
 * Created by d.ivaka on 16.03.2018.
 */

public class dialogDataReturn {

    public String mTitle = "";
    public int mPosition = -1;
    public int mGroup_ID;

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

    public void setGroup_ID(int group_ID) {
        mGroup_ID = group_ID;
    }

    public void setPosition(int position) {
        mPosition = position;
    }
}
