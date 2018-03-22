package ru.c0ner.babyplaner.Fragments;

/**
 * Created by d.ivaka on 15.03.2018.
 */

public class babyItemBase {
    public String mTitle;
    public int mParent;
    public int mGroupID;
    public int mID;

    public babyItemBase(String title) {
        mTitle = title;
        mParent = 0;
        mGroupID = 0;
        mID = 0;

    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public int getGroupID() {

        return mGroupID;
    }

    public void setGroupID(int groupID) {
        mGroupID = groupID;
    }

    public babyItemBase(String title, int parent) {
        mTitle = title;
        mParent = parent;
    }

    public babyItemBase(String title, int parent,int Groupe_id) {
        mTitle = title;
        mParent = parent;
        mGroupID = Groupe_id;
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





}

