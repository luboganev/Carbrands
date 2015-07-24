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

    private CarBrandDetailPresenterOutput mView;

    public CarBrandDetailModule(CarBrandDetailPresenterOutput view) {
        mView = view;
    }

    @Provides @Singleton public CarBrandDetailInteractorInput provideCarBrandDetailInteractor(DataStore dataStore) {
        return new CarBrandDetailInteractor(dataStore);
    }

    @Provides @Singleton public CarBrandDetailPresenterInput provideCarBrandDetailPresenter(CarBrandDetailInteractorInput interactor, Navigator navigator) {
        CarBrandDetailPresenter presenter = new CarBrandDetailPresenter(mView, interactor, navigator);
        interactor.setInteractorOutput(presenter);
        return presenter;
    }
}
