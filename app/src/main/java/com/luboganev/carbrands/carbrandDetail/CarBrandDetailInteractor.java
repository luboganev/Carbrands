package com.luboganev.carbrands.carbrandDetail;

import com.luboganev.carbrands.common.DataStore;
import com.luboganev.carbrands.common.DataStoreCallback;
import com.luboganev.carbrands.model.CarBrand;

import java.util.List;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 27.04.2015
 */
public class CarBrandDetailInteractor implements CarBrandDetailInteractorInput {
    private CarBrandDetailInteractorOutput mPresenter;
    private final DataStore mDataStore;
    private final long mCarBrandId;

    public CarBrandDetailInteractor(long carBrandId, DataStore dataStore) {
        mCarBrandId = carBrandId;
        mDataStore = dataStore;
    }

    @Override
    public void setInteractorOutput(CarBrandDetailInteractorOutput presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadCarBrandDetail() {
        mDataStore.resetFilters();
        mDataStore.filterCarBrandId(mCarBrandId);
        mDataStore.execute(mDataStoreCallback);
    }

    @Override
    public void destroy() {
        // TODO: clean up and cancel running requests
    }

    private final DataStoreCallback mDataStoreCallback = new DataStoreCallback() {
        @Override
        public void foundCarBrands(List<CarBrand> carBrands) {
            mPresenter.foundCarBrandDetail(new CarBrandDetailDisplayModel(carBrands.get(0)));
        }
    };

}
