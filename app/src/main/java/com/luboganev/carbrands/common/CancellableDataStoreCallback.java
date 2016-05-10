package com.luboganev.carbrands.common;

import com.luboganev.carbrands.model.CarBrand;

import java.util.List;

/**
 * Created by luboganev on 10/05/16.
 */
public abstract class CancellableDataStoreCallback implements DataStoreCallback {
    protected boolean isCancelled = false;

    public void cancel() {
        isCancelled = true;
    }

    @Override
    public void foundCarBrands(List<CarBrand> carBrands) {
        // do nothing
    }
}
