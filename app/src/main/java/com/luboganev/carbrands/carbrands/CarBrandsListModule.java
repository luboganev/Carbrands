package com.luboganev.carbrands.carbrands;

import com.luboganev.carbrands.application.AppModule;
import com.luboganev.carbrands.common.DataStore;
import com.luboganev.carbrands.common.LocationManager;
import com.luboganev.carbrands.common.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luboganev on 24/04/2015
 */
@Module(injects = CarBrandsListActivity.class,
        addsTo = AppModule.class,
        complete = false
)
public class CarBrandsListModule {

    @Provides @Singleton public CarBrandsInteractorInput provideCarBrandsInteractor(LocationManager locationManager, DataStore dataStore) {
        return new CarBrandsInteractor(locationManager, dataStore);
    }

    @Provides @Singleton public CarBrandsPresenterInput provideCarBrandsPresenter(CarBrandsInteractorInput interactor, Navigator navigator) {
        CarBrandsPresenter presenter = new CarBrandsPresenter(interactor, navigator);
        interactor.setInteractorOutput(presenter);
        return presenter;
    }
}
