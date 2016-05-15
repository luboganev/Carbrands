package com.luboganev.carbrands.carbrandDetail;

/**
 * Created by luboganev on 21/04/2015
 */
public interface CarBrandDetailInteractorInput {
    void setInteractorOutput(CarBrandDetailInteractorOutput presenter);

    void loadCarBrandDetail();

    void destroy();
}
