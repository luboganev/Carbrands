package com.luboganev.carbrands.carbrandDetail;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public interface CarBrandDetailInteractorInput {
    public void setInteractorOutput(CarBrandDetailInteractorOutput presenter);

    public void setCarBrandIdToLoad(long carBrandId);

    public void loadCarBrandDetail();
}
