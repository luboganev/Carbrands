package com.luboganev.carbrands.carbrands;

import android.support.annotation.NonNull;

import com.luboganev.carbrands.common.Navigator;

import java.util.List;

/**
 * Created by luboganev on 25/04/15.
 */
public class CarBrandsPresenter implements CarBrandsPresenterInput, CarBrandsInteractorOutput {
    private CarBrandsPresenterOutput mView;
    private final CarBrandsInteractorInput mInteractor;
    private final Navigator mNavigator;

    private boolean mShouldFilterByCurrentCountry = false;
    private List<CarBrandListDisplayModel> mLoadedData;
    private String mCurrentCountryName;

    public CarBrandsPresenter(CarBrandsInteractorInput interactor, Navigator navigator) {
        this.mInteractor = interactor;
        this.mNavigator = navigator;
    }

    // CarBrandsPresenterInput

    @Override
    public void setView(@NonNull CarBrandsPresenterOutput view) {
        mView = view;
    }

    @Override
    public void onViewShow() {
        if(mLoadedData != null) {
            mView.setCarBrands(mLoadedData);
            return;
        }

        updateUICountryName();

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
                updateUICountryName();
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
    public boolean isLocationFilterActive() {
        return mShouldFilterByCurrentCountry;
    }

    @Override
    public void destroy() {
        mInteractor.destroy();
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

    // Helper methods

    private void updateUICountryName() {
        if (mCurrentCountryName != null) {
            mView.updateCountryName(mCurrentCountryName);
        } else {
            mView.clearCountryName();
        }
    }
}
