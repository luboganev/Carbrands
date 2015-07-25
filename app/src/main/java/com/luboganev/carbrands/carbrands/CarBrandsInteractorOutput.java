package com.luboganev.carbrands.carbrands;

import java.util.List;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface CarBrandsInteractorOutput {
    void foundCarBrands(List<CarBrandListDisplayModel> carBrands);
    void currentCountryChanged(String newCountryName);
}
