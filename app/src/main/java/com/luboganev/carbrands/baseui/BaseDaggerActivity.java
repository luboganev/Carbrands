package com.luboganev.carbrands.baseui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.luboganev.carbrands.application.CarBrandsApplication;
import java.util.List;
import dagger.ObjectGraph;

/**
 *
 * This is a base Activity class, which abstracts away the boilerplate code
 * related to creating a Dagger scoped object graph and performing the injection
 * of dependencies.
 *
 * Created by Lyubomir Ganev (ganevlyu) on 30.12.2014
 */
public abstract class BaseDaggerActivity extends AppCompatActivity {
    /**
     * A Dagger extended object graph, relevant only to the current Activity. It may be null, if
     * the current Activity does not have its own object graph, i.e. it does not have its own modules.
     */
    private @Nullable ObjectGraph mActivityGraph;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If activity has its own modules, it creates a scoped object graph, by extending the
        // Application object graph, and saves a local reference to it.

        List<Object> modules = getActivityModules();
        if(modules != null && modules.size() > 0) {
            mActivityGraph = CarBrandsApplication.get(this).createScopedGraph(modules.toArray());
        }

        // Inject the current activity with dependencies if necessary
        if(shouldInjectSelf()) {

            // If a scoped graph exists, inject from it. Otherwise inject from the application object graph.
            if (mActivityGraph != null) {
                mActivityGraph.inject(this);
            } else {
                CarBrandsApplication.get(this).inject(this);
            }

            // Signal to Activities extending the BaseActivity, that the injection is completed
            onInjected(savedInstanceState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Cleaning up the reference to the scoped object graph if any.
        mActivityGraph = null;
    }

    /**
     *  This method should return a list of modules to be added as a scoped object graph
     *  to the Application's object graph. If it does not provide any modules,
     *  the current Activity does not generate its own scoped object graph and uses the
     *  Application's object graph instead.
     */
    protected @Nullable List<Object> getActivityModules() { return null; }

    /**
     *  This method returns whether the current Activity itself
     *  should be injected with dependencies
     */
    protected boolean shouldInjectSelf() { return false; }

    /**
     *  A helper method used to inject objects. It uses the Activity's the scoped object
     *  graph if one exists. In case the Activity does not have its own scoped object graph,
     *  the Application's object graph is used for the injection.
     *
     * @param object
     *      The object to be injected with dependencies
     */
    protected void inject(@NonNull Object object) {
        if(mActivityGraph == null) {
            CarBrandsApplication.get(this).inject(object);
        } else {
            mActivityGraph.inject(object);
        }
    }

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
