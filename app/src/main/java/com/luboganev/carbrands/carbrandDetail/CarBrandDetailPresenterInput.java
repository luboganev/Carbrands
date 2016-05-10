package com.luboganev.carbrands.carbrandDetail;

import android.support.annotation.NonNull;

/**
 * Created by luboganev on 21/04/2015
 */
public interface CarBrandDetailPresenterInput {
    void setView(@NonNull CarBrandDetailPresenterOutput view);
    void onViewShow();
    void destroy();
}
