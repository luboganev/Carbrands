package com.luboganev.carbrands.model;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public class CarBrandFounder {
    private String mFirstName;
    private String mLastName;

    public CarBrandFounder() {
        this.mFirstName = "";
        this.mLastName = "";
    }

    public CarBrandFounder(String firstName, String lastName) {
        mFirstName = firstName;
        mLastName = lastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }
}
