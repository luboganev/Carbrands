package com.luboganev.carbrands.carbrandDetail;

import android.os.Bundle;

import com.luboganev.carbrands.carbrands.CarBrandListDisplayModel;
import com.luboganev.carbrands.common.Navigator;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 27.04.2015
 */
public class CarBrandDetailPresenter implements CarBrandDetailPresenterInput, CarBrandDetailInteractorOutput {
    private final CarBrandDetailPresenterOutput mView;
    private final CarBrandDetailInteractorInput mInteractor;
    private final Navigator mNavigator;

    private static final String STATE_EXTRA_LOADED_DATA = "state_extra_loaded_data";
    private CarBrandDetailDisplayModel mLoadedData;

    public CarBrandDetailPresenter(CarBrandDetailPresenterOutput view, CarBrandDetailInteractorInput interactor, Navigator navigator) {
        mView = view;
        mInteractor = interactor;
        mNavigator = navigator;
    }

    // CarBrandDetailPresenterInput implementation

    @Override
    public void onViewShown() {
        if(mLoadedData != null) {
            mView.setCarBrand(mLoadedData);
            return;
        }

        reload();
    }

    private void reload() {
        mView.showProgress();
        mInteractor.loadCarBrandDetail();
    }

    @Override
    public void onViewSaveState(Bundle savedInstanceState) {
        if (mLoadedData != null) {
            savedInstanceState.putParcelable(STATE_EXTRA_LOADED_DATA, mLoadedData);
        }
    }

    @Override
    public void onViewCreated(Bundle launchingIntentExtras, Bundle savedInstanceState) {
        CarBrandListDisplayModel intentCarBrand = mNavigator.getCarBrandListDisplayModel(launchingIntentExtras);
        if(intentCarBrand != null) {
            mInteractor.setCarBrandIdToLoad(intentCarBrand.getCarBrandId());
            mView.setCarBrandName(intentCarBrand.getName());
        }

        if(savedInstanceState != null && savedInstanceState.containsKey(STATE_EXTRA_LOADED_DATA)) {
            mLoadedData = savedInstanceState.getParcelable(STATE_EXTRA_LOADED_DATA);
        }
    }

    // CarBrandDetailInteractorOutput implementation

    @Override
    public void foundCarBrandDetail(CarBrandDetailDisplayModel carBrandDetail) {
        mLoadedData = carBrandDetail;
        mView.setCarBrand(mLoadedData);
        mView.hideProgress();
    }
}
