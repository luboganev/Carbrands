package com.luboganev.carbrands.carbrandDetail;

import com.luboganev.carbrands.common.CancellableDataStoreCallback;
import com.luboganev.carbrands.common.DataStore;
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

        cancelDataCallback();
        mDataStoreCallback = new DataCallback();
        mDataStore.execute(mDataStoreCallback);
    }

    @Override
    public void destroy() {
        cancelDataCallback();
    }

    private void cancelDataCallback() {
        if (mDataStoreCallback != null) {
            mDataStoreCallback.cancel();
            mDataStoreCallback = null;
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
            mPresenter.foundCarBrandDetail(new CarBrandDetailDisplayModel(carBrands.get(0)));
        }
    }

}
