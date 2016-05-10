package com.luboganev.carbrands.application;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * This class represents a specific implementation of an Android Application, which contains
 * the necessary fields and functionality for initial setup of the Dagger dependency injection.
 *
 * Created by Lyubomir Ganev (ganevlyu) on 20.04.2015
 */
public class CarBrandsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Creating the root object graph from our root Dagger Module
        ObjectGraph applicationObjectGraph = ObjectGraph.create(new AppModule(this));
        ObjectGraphsHolder.getInstance().putApplicationObjectGraph(applicationObjectGraph);
    }
}
