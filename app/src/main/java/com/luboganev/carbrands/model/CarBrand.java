package com.luboganev.carbrands.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 20.04.2015
 */
public class CarBrand {
    public static final long STORE_ID_NONE = -1L;

    private long mId = STORE_ID_NONE;
    private String mName;
    private String mCountryCode;
    private String mLogoImageUrl;
    private List<CarBrandFounder> mFounders;

    public CarBrand(String name, String countryCode, String logoImageUrl) {
        mName = name;
        mCountryCode = countryCode;
        mLogoImageUrl = logoImageUrl;
        mFounders = new ArrayList<>();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public String getLogoImageUrl() {
        return mLogoImageUrl;
    }

    public void setLogoImageUrl(String logoImageUrl) {
        mLogoImageUrl = logoImageUrl;
    }

    public List<CarBrandFounder> getFounders() {
        return mFounders;
    }

    public void setFounders(List<CarBrandFounder> founders) {
        mFounders = founders;
    }
}
