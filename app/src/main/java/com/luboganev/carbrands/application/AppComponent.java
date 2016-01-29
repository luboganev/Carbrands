package com.luboganev.carbrands.application;

import com.luboganev.carbrands.carbrandDetail.CarBrandDetailActivity;
import com.luboganev.carbrands.carbrandDetail.CarBrandDetailModule;
import com.luboganev.carbrands.carbrands.CarBrandsListActivity;
import com.luboganev.carbrands.carbrands.CarBrandsListModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={
        CarBrandDetailModule.class,
        CarBrandsListModule.class
})
public interface AppComponent {


    void inject(CarBrandsApplication carBrandsApplication);

    void inject(CarBrandDetailActivity carBrandDetailActivity);

    void inject(CarBrandsListActivity carBrandsListActivity);

    final class Initializer {
        public static AppComponent init(CarBrandsApplication appContext) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(appContext))
                    .build();
        }
    }
}
