package ru.c0ner.babyplaner.Fragments;

/**
 * Created by d.ivaka on 15.03.2018.
 */

public class babyItemBase {
    public String mTitle; // название
    public int mParent; // ID родителя если нет -1
    public int mGroupID; // ID списка
    public int mID; // собственный ID онже используется как ID парент для подсписка.

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

    public babyItemBase(String title, int parent,int item_id) {
        mTitle = title;
        mParent = parent;
        mID = item_id;
        mGroupID=mID;
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

