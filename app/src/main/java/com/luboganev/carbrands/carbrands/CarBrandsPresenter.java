package com.luboganev.carbrands.carbrands;

import android.os.Bundle;

import com.luboganev.carbrands.common.Navigator;

import java.util.Arrays;
import java.util.List;

/**
 * Created by luboganev on 25/04/15.
 */
public class CarBrandsPresenter implements CarBrandsPresenterInput, CarBrandsInteractorOutput {
    private final CarBrandsPresenterOutput mView;
    private final CarBrandsInteractorInput mInteractor;
    private final Navigator mNavigator;

    private boolean mShouldFilterByCurrentCountry = false;
    private List<CarBrandListDisplayModel> mLoadedData;
    private String mCurrentCountryName;

    private static final String STATE_EXTRA_LOADED_DATA = "state_extra_loaded_data";
    private static final String STATE_EXTRA_COUNTRY_FILTER_ACTIVE = "state_extra_country_filter_active";
    private static final String STATE_EXTRA_COUNTRY_NAME = "state_extra_country_name";

    public CarBrandsPresenter(CarBrandsPresenterOutput view, CarBrandsInteractorInput interactor, Navigator navigator) {
        this.mView = view;
        this.mInteractor = interactor;
        this.mNavigator = navigator;
    }

    // CarBrandsPresenterInput

    @Override
    public void onViewShow() {
        if(mLoadedData != null) {
            mView.setCarBrands(mLoadedData);
            return;
        }

        reload();
    }

    @Override
    public void refreshRequested() {
        reload();
    }

    @Override
    public void filterChanged(boolean shouldFilterByCurrentCountry) {
        if(shouldFilterByCurrentCountry != mShouldFilterByCurrentCountry) {
            mShouldFilterByCurrentCountry = shouldFilterByCurrentCountry;
            if (!mShouldFilterByCurrentCountry) {
                mCurrentCountryName = null;
                mView.clearCountryName();
            }
            reload();
        }
    }

    private void reload() {
        mView.showProgress();
        mView.clearCarBrands();
        mInteractor.loadCarBrands(mShouldFilterByCurrentCountry);
    }

    @Override
    public void carBrandClicked(CarBrandListDisplayModel displayModel) {
        mNavigator.navigateToBrandDetail(displayModel);
    }

    @Override
    public void onViewSaveState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(STATE_EXTRA_COUNTRY_FILTER_ACTIVE, mShouldFilterByCurrentCountry);
        if (mLoadedData != null) {
            CarBrandListDisplayModel[] array = new CarBrandListDisplayModel[mLoadedData.size()];
            mLoadedData.toArray(array);
            savedInstanceState.putParcelableArray(STATE_EXTRA_LOADED_DATA, array);
        }
        if (mCurrentCountryName != null) {
            savedInstanceState.putString(STATE_EXTRA_COUNTRY_NAME, mCurrentCountryName);
        }
    }

    @Override
    public void onViewCreate(Bundle launchingIntentExtras, Bundle savedInstanceState) {
        if(savedInstanceState == null) {
            return;
        }

        mShouldFilterByCurrentCountry = savedInstanceState.getBoolean(STATE_EXTRA_COUNTRY_FILTER_ACTIVE, false);
        if (savedInstanceState.containsKey(STATE_EXTRA_LOADED_DATA)) {
            mLoadedData = Arrays.asList((CarBrandListDisplayModel[])savedInstanceState.getParcelableArray(STATE_EXTRA_LOADED_DATA));
        }
        if (savedInstanceState.containsKey(STATE_EXTRA_COUNTRY_NAME)) {
            mCurrentCountryName = savedInstanceState.getString(STATE_EXTRA_COUNTRY_NAME);
            mView.updateCountryName(mCurrentCountryName);
        } else {
            mView.clearCountryName();
        }
    }

    @Override
    public void onViewDestroy(boolean isExiting) {

    }

    @Override
    public boolean isLocationFilterActive() {
        return false;
    }

    // CarBrandsInteractorOutput

    @Override
    public void foundCarBrands(List<CarBrandListDisplayModel> carBrands) {
        mLoadedData = carBrands;
        mView.setCarBrands(carBrands);
        mView.hideProgress();
    }

    @Override
    public void currentCountryChanged(String newCountryName) {
        mCurrentCountryName = newCountryName;
        mView.updateCountryName(newCountryName);
    }
}
