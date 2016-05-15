package com.luboganev.carbrands.carbrands;

/**
 * Created by luboganev on 21/04/2015
 */
public interface CarBrandsInteractorInput {
    void setInteractorOutput(CarBrandsInteractorOutput presenter);

    void loadCarBrands(boolean shouldFilterByCurrentCountry);

    void destroy();
}
