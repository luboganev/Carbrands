package com.luboganev.carbrands.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A model of a Car brand, containing all information related to it. The names of the
 * car brand founders are stored as a list of the specific CarBrandFounder model.
 *
 * Created by Lyubomir Ganev (ganevlyu) on 20.04.2015
 */
public class CarBrand {
    public static final long STORE_ID_NONE = -1L;

    public long id = STORE_ID_NONE;
    public String name;
    public String countryCode;
    public String logoImageUrl;
    public List<CarBrandFounder> founders;

    public CarBrand(String name, String countryCode, String logoImageUrl) {
        this.name = name;
        this.countryCode = countryCode;
        this.logoImageUrl = logoImageUrl;
        founders = new ArrayList<>();
    }
}
