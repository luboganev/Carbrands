package com.luboganev.carbrands.carbrands;

import com.luboganev.carbrands.application.AppModule;
import com.luboganev.carbrands.common.DataStore;
import com.luboganev.carbrands.common.LocationManager;
import com.luboganev.carbrands.common.Navigator;
import com.luboganev.carbrands.common.NavigatorModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 24.04.2015
 */
@Module(includes = {
        AppModule.class,
        NavigatorModule.class
})
public class CarBrandsListModule {

    private CarBrandsPresenterOutput mView;

    public CarBrandsListModule(CarBrandsPresenterOutput view) {
        mView = view;
    }

    @Provides @Singleton public CarBrandsInteractorInput provideCarBrandsInteractor(LocationManager locationManager, DataStore dataStore) {
        return new CarBrandsInteractor(locationManager, dataStore);
    }

    @Provides @Singleton public CarBrandsPresenterInput provideCarBrandsPresenter(CarBrandsInteractorInput interactor, Navigator navigator) {
        CarBrandsPresenter presenter = new CarBrandsPresenter(mView, interactor, navigator);
        interactor.setInteractorOutput(presenter);
        return presenter;
    }
}
