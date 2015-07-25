package com.luboganev.carbrands.carbrands;

import com.luboganev.carbrands.common.ViewLifecycleCallbacks;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface CarBrandsPresenterInput extends ViewLifecycleCallbacks {
    void refreshRequested();
    void filterChanged(boolean shouldFilterByCurrentCountry);
    void carBrandClicked(CarBrandListDisplayModel displayModel);
    boolean isLocationFilterActive();
}
