package ru.c0ner.babyplaner.Fragments;

/**
 * Created by d.ivaka on 15.03.2018.
 */

public class babyItemBase {
    public String mTitle;

    public babyItemBase(String title) {
        mTitle = title;
        mParent = -1;
    }

    public babyItemBase(String title, int parent) {
        mTitle = title;
        mParent = parent;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getParent() {
        return mParent;
    }

    public void setParent(int parent) {
        mParent = parent;
    }

    public int mParent;



}

