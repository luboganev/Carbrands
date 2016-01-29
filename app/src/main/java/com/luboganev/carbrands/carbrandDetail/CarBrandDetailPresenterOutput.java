package com.luboganev.carbrands.carbrandDetail;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface CarBrandDetailPresenterOutput {
    void showProgress();

    void hideProgress();

    void setCarBrand(CarBrandDetailDisplayModel carBrand);

    void setCarBrandName(String carBrandName);
}
