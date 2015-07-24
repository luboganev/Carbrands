package com.luboganev.carbrands.application;


import com.luboganev.carbrands.carbrandDetail.CarBrandDetailModule;
import com.luboganev.carbrands.carbrands.CarBrandsListModule;
import com.luboganev.carbrands.common.NavigatorModule;

import dagger.Module;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 20.04.2015
 */
@Module(
        includes = {
                AppModule.class,
                NavigatorModule.class,
                CarBrandsListModule.class,
                CarBrandDetailModule.class
        }
)
public class CompileTimeValidationModule {
}
