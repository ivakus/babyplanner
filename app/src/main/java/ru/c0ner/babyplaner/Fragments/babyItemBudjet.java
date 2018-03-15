package ru.c0ner.babyplaner.Fragments;

/**
 * Created by d.ivaka on 15.03.2018.
 */


public class babyItemBudjet extends babyItemBase {
    public int mPrice;
    public int mPriceTeoritek;
    public int mKolvo;
    public int mGoodprice;

    public babyItemBudjet(String title) {
        super(title);
    }

    public babyItemBudjet(String title, int parent, int price, int priceTeoritek, int kolvo, int goodprice) {
        super(title, parent);
        mPrice = price;
        mPriceTeoritek = priceTeoritek;
        mKolvo = kolvo;
        mGoodprice = goodprice;
    }


    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public int getPriceTeoritek() {
        return mPriceTeoritek;
    }

    public void setPriceTeoritek(int priceTeoritek) {
        mPriceTeoritek = priceTeoritek;
    }

    public int getKolvo() {
        return mKolvo;
    }

    public void setKolvo(int kolvo) {
        mKolvo = kolvo;
    }

    public int getGoodprice() {
        return mGoodprice;
    }

    public void setGoodprice(int goodprice) {
        mGoodprice = goodprice;
    }
}