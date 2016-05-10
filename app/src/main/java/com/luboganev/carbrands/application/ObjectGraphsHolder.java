package com.luboganev.carbrands.application;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import dagger.ObjectGraph;

/**
 * A simple singleton holder containing mapping from a class to an {@link ObjectGraph}. It contains
 * all {@link ObjectGraph} instances related to different components of the application.
 * <p/>
 * Created by luboganev on 12/02/16.
 */
public class ObjectGraphsHolder {

    /**
     * The singleton pattern instance
     */
    private static ObjectGraphsHolder instance;

    /**
     *  Returns the single instance of the {@link ObjectGraphsHolder}
     */
    public static ObjectGraphsHolder getInstance() {
        if (instance == null) {
            instance = new ObjectGraphsHolder();
        }

        return instance;
    }

    private ObjectGraphsHolder() {
        objectGraphMap = new HashMap<>();
        objectGraphIdAutoIncrement = 0L;
    }

    /**
     *  The map containing all {@link ObjectGraph} instances
     */
    private Map<Long, ObjectGraph> objectGraphMap;

    /**
     *  The root application {@link ObjectGraph}
     */
    private ObjectGraph applicationObjectGraph;

    /**
     *  An auto incremented counter used to generate unique ids
     */
    private long objectGraphIdAutoIncrement;

    /**
     *  Retrieves the root application {@link ObjectGraph}
     */
    public @NonNull
    ObjectGraph getApplicationObjectGraph() {
        return applicationObjectGraph;
    }

    /**
     *  Sets the root application {@link ObjectGraph}
     */
    public void putApplicationObjectGraph(@NonNull ObjectGraph objectGraph) {
        applicationObjectGraph = objectGraph;
    }

    /**
     *  Retrieves the {@link ObjectGraph} from the holder associated with the input id
     */
    public @Nullable
    ObjectGraph getObjectGraph(long objectGraphId) {
        return objectGraphMap.get(objectGraphId);
    }

    /**
     *  Sstores the {@link ObjectGraph} in the holder and returns the id associated with it
     */
    public long putObjectGraph(@NonNull ObjectGraph objectGraph) {
        objectGraphMap.put(objectGraphIdAutoIncrement, objectGraph);
        return objectGraphIdAutoIncrement++;
    }

    /**
     *  Removes the {@link ObjectGraph} associated with the input id
     */
    public void removeObjectGraph(long objectGraphId) {
        objectGraphMap.remove(objectGraphId);
    }

    /**
     * A helper method used to create a scoped object graph.
     *
     * @param parentObjectGraph
     *      The objecgraph to be used as parent for the scoped {@link ObjectGraph}
     *
     * @param modules
     *      The extra dagger modules which will be added to
     *      create a new {@link ObjectGraph}. <br/>
     *      If this list is empty or null, this returns the input parentObjectGraph
     */
    public static @NonNull
    ObjectGraph createScopedGraph(@NonNull ObjectGraph parentObjectGraph,
                                  @Nullable Object... modules) {
        if (modules != null && modules.length > 0) {
            return parentObjectGraph.plus(modules);
        }

        return parentObjectGraph;
    }
}
