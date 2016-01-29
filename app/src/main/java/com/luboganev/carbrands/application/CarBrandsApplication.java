package com.luboganev.carbrands.application;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;

/**
 * This class represents a specific implementation of an Android Application, which contains
 * the necessary fields and functionality for initial setup of the Dagger dependency injection.
 * <p>
 * Created by Lyubomir Ganev (ganevlyu) on 20.04.2015
 */
public class CarBrandsApplication extends Application {

    /**
     * The root object graph of the application
     */
    private AppComponent component;

    /**
     * A helper method which gets a reference to the Application from an input Activity instance
     */
    public static CarBrandsApplication get(@NonNull Activity activity) {
        return (CarBrandsApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = AppComponent.Initializer.init(this);
        component.inject(this);
    }

    public AppComponent component() {
        return component;
    }
}
