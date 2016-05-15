package com.luboganev.carbrands.carbrands;

import com.luboganev.carbrands.common.CancellableDataStoreCallback;
import com.luboganev.carbrands.common.CancellableLocationManagerCallback;
import com.luboganev.carbrands.common.CountryNamesHelper;
import com.luboganev.carbrands.common.DataStore;
import com.luboganev.carbrands.common.LocationManager;
import com.luboganev.carbrands.model.CarBrand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luboganev on 25/04/15.
 */
public class CarBrandsInteractor implements CarBrandsInteractorInput {
    private CarBrandsInteractorOutput mPresenter;
    private final LocationManager mLocationManager;
    private final DataStore mDataStore;

    public CarBrandsInteractor(LocationManager locationManager, DataStore dataStore) {
        mLocationManager = locationManager;
        mDataStore = dataStore;
    }

    @Override
    public void setInteractorOutput(CarBrandsInteractorOutput presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadCarBrands(boolean shouldFilterByCurrentCountry) {
        if(shouldFilterByCurrentCountry) {
            cancelLocationCallback();
            mLocationCallback = new LocationCallback();
            mLocationManager.requestUpdateCountryLocation(mLocationCallback);
            return;
        }

        cancelDataCallback();
        mDataStoreCallback = new DataCallback();
        mDataStore.resetFilters();
        mDataStore.execute(mDataStoreCallback);
    }

    @Override
    public void destroy() {
        if (mLocationCallback != null) {
            mLocationCallback.cancel();
        }
        if (mDataStoreCallback != null) {
            mDataStoreCallback.cancel();
        }
    }

    private LocationCallback mLocationCallback;

    private class LocationCallback extends CancellableLocationManagerCallback {

        @Override
        public void currentCountryCode(String countryCode) {
            if (isCancelled) {
                return;
            }

            cancelLocationCallback();
            mPresenter.currentCountryChanged(CountryNamesHelper.getCountryName(countryCode));
            mDataStore.resetFilters();
            mDataStore.filterCountryCode(countryCode);

            cancelDataCallback();
            mDataStoreCallback = new DataCallback();
            mDataStore.execute(mDataStoreCallback);
        }
    }

    private void cancelDataCallback() {
        if (mDataStoreCallback != null) {
            mDataStoreCallback.cancel();
            mDataStoreCallback = null;
        }
    }

    private void cancelLocationCallback() {
        if (mLocationCallback != null) {
            mLocationCallback.cancel();
            mLocationCallback = null;
        }
    }

    private DataCallback mDataStoreCallback;

    private class DataCallback extends CancellableDataStoreCallback {
        @Override
        public void foundCarBrands(List<CarBrand> carBrands) {
            if (isCancelled) {
                return;
            }

            cancelDataCallback();
            mPresenter.foundCarBrands(convertToDisplayModel(carBrands));
        }
    }

    private List<CarBrandListDisplayModel> convertToDisplayModel(List<CarBrand> carBrands) {
        List<CarBrandListDisplayModel> result = new ArrayList<>();
        for (CarBrand carBrand : carBrands) {
            result.add(new CarBrandListDisplayModel(carBrand));
        }
        return result;
    }
}
