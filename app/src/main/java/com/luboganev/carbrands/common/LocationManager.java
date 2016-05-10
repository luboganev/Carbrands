package com.luboganev.carbrands.common;

/**
 * This interface defines a basic location functionality. It is designed to be asynchronous, i.e.
 * all its methods return immediately and any results are delivered through another callback interface.
 * It abstracts any underlying implementation which might be a standart Android Location provider,
 * Google Play Service database or another service.
 *
 * Created by luboganev on 21/04/2015
 */
public interface LocationManager {
    void requestUpdateCountryLocation(LocationManagerCallback callback);
}


