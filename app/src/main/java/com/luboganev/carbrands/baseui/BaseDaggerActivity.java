package com.luboganev.carbrands.baseui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.luboganev.carbrands.application.ObjectGraphsHolder;
import com.luboganev.carbrands.common.Navigator;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * This is a base Activity class, which abstracts away the boilerplate code
 * related to creating a Dagger scoped object graph and performing the injection
 * of dependencies.
 * <p/>
 * Created by luboganev on 30/12/2014
 */
public abstract class BaseDaggerActivity extends AppCompatActivity {

    /**
     *  The singleton Navigator object of the application
     */
    @Inject Navigator navigator;

    /**
     * The id of the object graph used for injecting dependencies in the current activity
     */
    private long objectGraphId = -1;

    /**
     * The key used to save and restore the {@link #objectGraphId} through configuration changes
     */
    private static final String STATE_EXTRA_OBJECT_GRAPH_ID = "objectgraph_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check for stored id and restore it
        if (savedInstanceState != null) {
            objectGraphId = savedInstanceState.getLong(STATE_EXTRA_OBJECT_GRAPH_ID, -1);
        }

        // Check if an object graph already exists for this activity
        ObjectGraph activityObjectGraph = ObjectGraphsHolder.getInstance().getObjectGraph(objectGraphId);

        if (activityObjectGraph == null) {
            // Create a new object graph, which adds to the root application object graph
            ObjectGraph rootObjectGraph = ObjectGraphsHolder.getInstance().getApplicationObjectGraph();
            activityObjectGraph = ObjectGraphsHolder.createScopedGraph(rootObjectGraph, getActivityModules());
            objectGraphId = ObjectGraphsHolder.getInstance().putObjectGraph(activityObjectGraph);
        }

        // Inject dependencies
        activityObjectGraph.inject(this);

        // Make sure to update the navigator's current activity
        navigator.setActivity(this);
        onInjected(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Store the current object graph id to survive configuration change
        outState.putLong(STATE_EXTRA_OBJECT_GRAPH_ID, objectGraphId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Any other call to this method is due to configuration change or low memory.
        // We want to release the stored object graph only when the activity is truly
        // finishing.
        if (isFinishing()) {
            onDestroyObjectGraph();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Make sure to update the navigator's current activity
        navigator.setActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Make sure to update the navigator's current activity
        navigator.setActivity(this);
    }

    protected void onDestroyObjectGraph() {
        // Remove the current object graph from the holder
        ObjectGraphsHolder.getInstance().removeObjectGraph(objectGraphId);
    }

    /**
     *  This method should return a list of modules to be added as a scoped object graph
     *  to the Application's object graph. If it does not provide any modules,
     *  the current Activity does not generate its own scoped object graph and uses the
     *  Application's object graph instead.
     */
    protected @Nullable Object[] getActivityModules() { return null; }

    /**
     * This method gets called once the Activity has been injected.
     * It is a hook you can use if you want to do something as early as
     * in the onCreate() but still need the injected dependencies
     *
     * @param savedInstanceState
     *      The reference to the savedInstanceState from the onCreate method.
     */
    protected void onInjected(@Nullable Bundle savedInstanceState) {}
}
