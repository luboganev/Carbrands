package com.luboganev.carbrands.baseui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.luboganev.carbrands.application.CarBrandsApplication;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 30.12.2014
 */
public abstract class BaseDaggerActivity extends AppCompatActivity {
    private ObjectGraph mActivityGraph;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Object> modules = getActivityModules();
        if(modules != null && modules.size() > 0) {
            mActivityGraph = CarBrandsApplication.get(this).createScopedGraph(modules.toArray());
            if(shouldInjectSelf()) {
                mActivityGraph.inject(this);
                onInjected(savedInstanceState);
            }
            return;
        }

        if(shouldInjectSelf()) {
            CarBrandsApplication.get(this).inject(this);
            onInjected(savedInstanceState);
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mActivityGraph = null;
    }

    /**
     *  This method returns a list of modules to be added as a scoped sub-graph
     *  to the Application's object graph. If it does not provide any modules,
     *  the current Activity does not generate its own sub-graph and uses the
     *  Application's object graph instead.
     */
    protected List<Object> getActivityModules() { return null; }

    /**
     *  This method returns whether the current Activity itself
     *  should be injected with dependencies
     */
    protected boolean shouldInjectSelf() { return false; }

    /**
     *  A helper method used to inject objects from the scoped graph of
     *  this Activity
     *
     * @param object
     *      The object to be injected
     */
    protected void inject(Object object) {
        if(mActivityGraph == null) {
            CarBrandsApplication.get(this).inject(object);
            return;
        }

        mActivityGraph.inject(object);
    }

    /**
     * This method gets called once the Activity has been injected.
     * It is a hook you can use if you want to do something as early as
     * in the onCreate() but still need the injected dependencies
     *
     * @param savedInstanceState
     */
    protected void onInjected(Bundle savedInstanceState) {}
}
