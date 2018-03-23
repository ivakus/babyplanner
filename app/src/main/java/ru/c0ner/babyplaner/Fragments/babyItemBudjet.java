package ru.c0ner.babyplaner.Fragments;

/**
 * Created by d.ivaka on 15.03.2018.
 */


public class babyItemBudjet extends babyItemBase {
    public int mPrice_real;
    public int mPrice_plan;
    public int mKolvo;
    public int mGoodprice;

    public babyItemBudjet(String title) {
        super(title);

    }

    public babyItemBudjet(String title, int parent, int price_plan, int price_real, int kolvo, int goodprice) {
        super(title, parent);
        mPrice_plan = price_plan;
        mPrice_real = price_real;
        mKolvo = kolvo;
        mGoodprice = 0;
    }


    public int getPrice() {
        return mPrice_plan;
    }

    public void setPrice(int price) {
        mPrice_plan = price;
    }

    public int getPriceReal() {
        return mPrice_real;
    }

    public void setPriceReal(int priceTeoritek) {
        mPrice_real = priceTeoritek;
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