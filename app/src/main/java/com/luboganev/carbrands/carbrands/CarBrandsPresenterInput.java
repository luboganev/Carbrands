package com.luboganev.carbrands.carbrands;

import com.luboganev.carbrands.common.ActivityPresenterInput;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface CarBrandsPresenterInput extends ActivityPresenterInput {
    public void refreshRequested();
    public void filterChanged(boolean shouldFilterByCurrentCountry);
    public void carBrandClicked(CarBrandListDisplayModel displayModel);
    public boolean isLocationFilterActive();
}
