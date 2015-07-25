package com.luboganev.carbrands.carbrandDetail;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface CarBrandDetailInteractorInput {
    void setInteractorOutput(CarBrandDetailInteractorOutput presenter);

    void setCarBrandIdToLoad(long carBrandId);

    void loadCarBrandDetail();
}
