package com.luboganev.carbrands.common;

import android.app.Activity;

import com.luboganev.carbrands.application.AppModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
@Module(
        addsTo = AppModule.class,
        library = true
)
public class NavigatorModule {
    private final Activity mActivity;

    public NavigatorModule(Activity activity) {
        mActivity = activity;
    }

    @Provides Navigator provideNavigator() {
        return new Navigator(mActivity);
    }
}
