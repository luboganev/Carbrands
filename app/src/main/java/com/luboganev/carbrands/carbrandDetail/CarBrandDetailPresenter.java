package com.luboganev.carbrands.carbrandDetail;

import android.support.annotation.NonNull;

import com.luboganev.carbrands.common.Navigator;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 27.04.2015
 */
public class CarBrandDetailPresenter implements CarBrandDetailPresenterInput, CarBrandDetailInteractorOutput {
    private CarBrandDetailPresenterOutput mView;
    private final CarBrandDetailInteractorInput mInteractor;
    private final Navigator mNavigator;
    private final String mCarBrandName;

    private CarBrandDetailDisplayModel mLoadedData;

    public CarBrandDetailPresenter(String carBrandName,
                                   CarBrandDetailInteractorInput interactor,
                                   Navigator navigator) {
        mCarBrandName = carBrandName;
        mInteractor = interactor;
        mNavigator = navigator;
    }

    // CarBrandDetailPresenterInput implementation

    @Override
    public void setView(@NonNull CarBrandDetailPresenterOutput view) {
        mView = view;
        mView.setCarBrandName(mCarBrandName);
    }

    @Override
    public void onViewShow() {
        if(mLoadedData != null) {
            mView.setCarBrand(mLoadedData);
            return;
        }

        reload();
    }

    @Override
    public void destroy() {
        mInteractor.destroy();
    }

    private void reload() {
        mView.showProgress();
        mInteractor.loadCarBrandDetail();
    }

    // CarBrandDetailInteractorOutput implementation

    @Override
    public void foundCarBrandDetail(CarBrandDetailDisplayModel carBrandDetail) {
        mLoadedData = carBrandDetail;
        mView.setCarBrand(mLoadedData);
        mView.hideProgress();
    }
}
