package com.luboganev.carbrands.common;

/**
 * Created by luboganev on 10/05/16.
 */
public class CancellableLocationManagerCallback implements LocationManagerCallback {
    protected boolean isCancelled = false;

    public void cancel() {
        isCancelled = true;
    }

    @Override
    public void currentCountryCode(String countryCode) {
        // do nothing here
    }
}
