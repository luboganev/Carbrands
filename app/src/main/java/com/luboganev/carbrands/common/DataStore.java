package com.luboganev.carbrands.common;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface DataStore {
    public void resetFilters();
    public void filterCountryCode(String countryCode);
    public void filterCarBrandId(long id);
    public void execute(DataStoreCallback callback);
}
