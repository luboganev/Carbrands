package com.luboganev.carbrands.carbrands;

import java.util.List;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface CarBrandsPresenterOutput {
    void showProgress();
    void hideProgress();
    void setCarBrands(List<CarBrandListDisplayModel> carBrands);
    void clearCarBrands();
    void updateCountryName(String newCountryName);
    void clearCountryName();
}
