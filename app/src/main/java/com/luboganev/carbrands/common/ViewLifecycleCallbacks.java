package com.luboganev.carbrands.common;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * This interface defines some important Activity lifecycle callbacks, which most probably
 * every Presenter would care knowing about
 *
 * Created by Lyubomir Ganev (ganevlyu) on 27.04.2015
 */
public interface ViewLifecycleCallbacks {

    /**
     * Called when an activity's onResume method is invoked
     */
    void onViewShow();

    /**
     * Called when an activity's onSaveInstanceState method is invoked
     *
     * @param savedInstanceState
     *      The bundle to use for saving state
     */
    void onViewSaveState(Bundle savedInstanceState);

    /**
     * Called when an activity's has just been created.
     *
     * @param launchingIntentExtras
     *      This bundle contains the extras from the Activity's launching Intent. It may be null.
     * @param savedInstanceState
     *      This bundle contains the extras of the saved instance state. It may be null;
     */
    void onViewCreate(@Nullable Bundle launchingIntentExtras, @Nullable Bundle savedInstanceState);

    /**
     * Called when an activity's onDestroy is invoked.
     *
     * @param isExiting
     *      If the activity's onDestroy is a result of the Activity going away. If this flag is false,
     *      this probably means that the Activity is being destroyed due to a configuration change or
     *      a low memory situation and will be re-created again.
     */
    void onViewDestroy(boolean isExiting);
}
