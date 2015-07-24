package com.luboganev.carbrands.application;

import android.app.Activity;
import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 20.04.2015
 */
public class CarBrandsApplication extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(new AppModule(this));
    }

    public ObjectGraph createScopedGraph(Object... modules) {
        return objectGraph.plus(modules);
    }

    /**
     *  A helper method which injects objects with their dependencies from the
     *  the Application's object graph
     *
     * @param object
     *      The object to be injected
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    /**
     *  A helper method which extracts the Application from an input Activity
     */
    public static CarBrandsApplication get(Activity activity) {
        return (CarBrandsApplication) activity.getApplication();
    }
}
