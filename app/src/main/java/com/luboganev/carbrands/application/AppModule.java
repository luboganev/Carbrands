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
 * This is the root Dagger module, which is created when the application is started.
 * It is not related to any UI element, therefore the objects provided by it have lifecycle as long
 * as the Application process itself.
 *
 * Created by Lyubomir Ganev (ganevlyu) on 20.04.2015
 */
@Module
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
