package com.luboganev.carbrands.model;

/**
 * A model containing the names of a single car brand founder
 *
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public class CarBrandFounder {
    public String firstName;
    public String lastName;

    public CarBrandFounder() {
        firstName = "";
        lastName = "";
    }

    public CarBrandFounder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
