package com.luboganev.carbrands.carbrands;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface CarBrandsInteractorInput {
    public void setInteractorOutput(CarBrandsInteractorOutput presenter);

    public void loadCarBrands(boolean shouldFilterByCurrentCountry);
}
