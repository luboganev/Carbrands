package com.luboganev.carbrands.carbrands;

import java.util.List;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface CarBrandsPresenterOutput {
    public void showProgress();
    public void hideProgress();
    public void setCarBrands(List<CarBrandListDisplayModel> carBrands);
    public void clearCarBrands();
    public void updateCountryName(String newCountryName);
    public void clearCountryName();
}
