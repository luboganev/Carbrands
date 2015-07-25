package com.luboganev.carbrands.common;

import com.luboganev.carbrands.model.CarBrand;

import java.util.List;

/**
 * A callback of the DataStore execute operation, containing the results of the request.
 *
 * Created by luboganev on 25/04/15.
 */
public interface DataStoreCallback {
    void foundCarBrands(List<CarBrand> carBrands);
}
