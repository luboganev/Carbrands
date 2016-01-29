package com.luboganev.carbrands.baseui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.luboganev.carbrands.application.CarBrandsApplication;
import java.util.List;

/**
 *
 * This is a base Activity class, which abstracts away the boilerplate code
 * related to creating a Dagger scoped object graph and performing the injection
 * of dependencies.
 *
 * Created by Lyubomir Ganev (ganevlyu) on 30.12.2014
 */
public abstract class BaseDaggerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        daggerInject();
        super.onCreate(savedInstanceState);
    }

    protected abstract void daggerInject();

}
