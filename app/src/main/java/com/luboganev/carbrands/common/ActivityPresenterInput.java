package com.luboganev.carbrands.common;

import android.os.Bundle;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 27.04.2015
 */
public interface ActivityPresenterInput {
    public void onViewShown();
    public void onViewSaveState(Bundle savedInstanceState);
    public void onViewCreated(Bundle launchingIntentExtras, Bundle savedInstanceState);
}
