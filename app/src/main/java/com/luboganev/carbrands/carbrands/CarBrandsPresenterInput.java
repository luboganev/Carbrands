package com.luboganev.carbrands.carbrands;

import android.support.annotation.NonNull;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface CarBrandsPresenterInput {
    void setView(@NonNull CarBrandsPresenterOutput view);
    void onViewShow();
    void refreshRequested();
    void filterChanged(boolean shouldFilterByCurrentCountry);
    void carBrandClicked(CarBrandListDisplayModel displayModel);
    boolean isLocationFilterActive();
    void destroy();
}
