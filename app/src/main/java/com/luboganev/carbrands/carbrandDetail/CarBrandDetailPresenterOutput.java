package com.luboganev.carbrands.carbrandDetail;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface CarBrandDetailPresenterOutput {
    public void showProgress();
    public void hideProgress();
    public void setCarBrand(CarBrandDetailDisplayModel carBrand);
    public void setCarBrandName(String carBrandName);
}
