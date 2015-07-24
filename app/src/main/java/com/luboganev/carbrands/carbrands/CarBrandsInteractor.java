package com.luboganev.carbrands.carbrands;

import com.luboganev.carbrands.common.CountryNamesHelper;
import com.luboganev.carbrands.common.DataStore;
import com.luboganev.carbrands.common.DataStoreCallback;
import com.luboganev.carbrands.common.LocationManager;
import com.luboganev.carbrands.common.LocationManagerCallback;
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
            mLocationManager.requestUpdateCountryLocation(mLocationCallback);
            return;
        }

        mDataStore.resetFilters();
        mDataStore.execute(mDataStoreCallback);
    }

    private final LocationManagerCallback mLocationCallback = new LocationManagerCallback() {
        @Override
        public void currentCountryCode(String countryCode) {
            mPresenter.currentCountryChanged(CountryNamesHelper.getCountryName(countryCode));
            mDataStore.resetFilters();
            mDataStore.filterCountryCode(countryCode);
            mDataStore.execute(mDataStoreCallback);
        }
    };

    private final DataStoreCallback mDataStoreCallback = new DataStoreCallback() {
        @Override
        public void foundCarBrands(List<CarBrand> carBrands) {
            mPresenter.foundCarBrands(convertToDisplayModel(carBrands));
        }
    };

    private List<CarBrandListDisplayModel> convertToDisplayModel(List<CarBrand> carBrands) {
        List<CarBrandListDisplayModel> result = new ArrayList<>();
        for (CarBrand carBrand : carBrands) {
            result.add(new CarBrandListDisplayModel(carBrand));
        }
        return result;
    }
}
