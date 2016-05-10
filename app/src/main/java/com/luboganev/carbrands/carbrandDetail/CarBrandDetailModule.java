package com.luboganev.carbrands.carbrandDetail;

import com.luboganev.carbrands.application.AppModule;
import com.luboganev.carbrands.common.DataStore;
import com.luboganev.carbrands.common.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 24.04.2015
 */
@Module(injects = CarBrandDetailActivity.class,
        addsTo = AppModule.class,
        complete = false)
public class CarBrandDetailModule {
    private final long mCarBrandId;
    private final String mCarBrandName;

    public CarBrandDetailModule(long carBrandId, String carBrandName) {
        mCarBrandId = carBrandId;
        mCarBrandName = carBrandName;
    }

    @Provides @Singleton public CarBrandDetailInteractorInput provideCarBrandDetailInteractor(DataStore dataStore) {
        return new CarBrandDetailInteractor(mCarBrandId, dataStore);
    }

    @Provides @Singleton public CarBrandDetailPresenterInput provideCarBrandDetailPresenter(CarBrandDetailInteractorInput interactor, Navigator navigator) {
        CarBrandDetailPresenter presenter = new CarBrandDetailPresenter(mCarBrandName, interactor, navigator);
        interactor.setInteractorOutput(presenter);
        return presenter;
    }
}
