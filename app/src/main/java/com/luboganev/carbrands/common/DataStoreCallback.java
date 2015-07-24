package com.luboganev.carbrands.common;

import com.luboganev.carbrands.model.CarBrand;

import java.util.List;

/**
 * Created by luboganev on 25/04/15.
 */
public interface DataStoreCallback {
    public void foundCarBrands(List<CarBrand> carBrands);
}
