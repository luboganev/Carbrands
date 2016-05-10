package com.luboganev.carbrands.common;

/**
 * This interface defines a callback from the LocationManager containing the countrycode
 * of the current user location.
 *
 * Created by luboganev on 24/04/2015
 */
public interface LocationManagerCallback {
    void currentCountryCode(String countryCode);
}
