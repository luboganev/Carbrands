package com.luboganev.carbrands.application;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import dagger.ObjectGraph;

/**
 * This class represents a specific implementation of an Android Application, which contains
 * the necessary fields and functionality for initial setup of the Dagger dependency injection.
 *
 * Created by Lyubomir Ganev (ganevlyu) on 20.04.2015
 */
public class CarBrandsApplication extends Application {

    /**
     * The root object graph of the application
     */
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        // Creating the root object graph from our root Dagger Module
        objectGraph = ObjectGraph.create(new AppModule(this));
    }

    /**
     * A helper method, which creates a scoped Dagger object graph, by adding the input
     * modules to the root Application object graph.
     *
     * @param modules
     *      The additional modules which should be included in the scoped object graph.
     * @return
     *      An instance of the created scoped object graph
     */
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
    public void inject(@NonNull Object object) {
        objectGraph.inject(object);
    }

    /**
     *  A helper method which gets a reference to the Application from an input Activity instance
     */
    public static CarBrandsApplication get(@NonNull Activity activity) {
        return (CarBrandsApplication) activity.getApplication();
    }
}
