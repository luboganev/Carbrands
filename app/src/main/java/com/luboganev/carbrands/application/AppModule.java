package com.luboganev.carbrands.application;

import android.content.Context;

import com.luboganev.carbrands.common.DataStore;
import com.luboganev.carbrands.common.LocationManager;
import com.luboganev.carbrands.common.MockDataStoreImpl;
import com.luboganev.carbrands.common.MockLocationManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 20.04.2015
 */
@Module(
        library = true
)
public class AppModule {
    private CarBrandsApplication application;

    public AppModule(CarBrandsApplication application) {
        this.application = application;
    }

    @Provides @Singleton public Context provideStaticApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides @Singleton public LocationManager provideLocationManager() {
        return new MockLocationManagerImpl();
    }

    @Provides @Singleton public DataStore provideDataStore() {
        return new MockDataStoreImpl();
    }
}
