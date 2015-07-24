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

    private long mCarBrandIdToLoad = CarBrand.STORE_ID_NONE;

    public CarBrandDetailInteractor(DataStore dataStore) {
        mDataStore = dataStore;
    }

    @Override
    public void setInteractorOutput(CarBrandDetailInteractorOutput presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setCarBrandIdToLoad(long carBrandId) {
        mCarBrandIdToLoad = carBrandId;
    }

    @Override
    public void loadCarBrandDetail() {
        mDataStore.resetFilters();
        mDataStore.filterCarBrandId(mCarBrandIdToLoad);
        mDataStore.execute(mDataStoreCallback);
    }

    private final DataStoreCallback mDataStoreCallback = new DataStoreCallback() {
        @Override
        public void foundCarBrands(List<CarBrand> carBrands) {
            mPresenter.foundCarBrandDetail(new CarBrandDetailDisplayModel(carBrands.get(0)));
        }
    };

}
