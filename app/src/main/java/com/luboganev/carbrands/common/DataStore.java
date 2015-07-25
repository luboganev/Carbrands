package com.luboganev.carbrands.common;

/**
 * This interface defines a basic data store functionality. It is designed to be asynchronous, i.e.
 * all its methods return immediately and any results are delivered through another callback interface.
 * It abstracts any underlying implementation which might be a local database or even a remote REST
 * API accessed over network.
 *
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface DataStore {
    void resetFilters();
    void filterCountryCode(String countryCode);
    void filterCarBrandId(long id);
    void execute(DataStoreCallback callback);
}
