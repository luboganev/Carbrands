package com.luboganev.carbrands.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.luboganev.carbrands.carbrandDetail.CarBrandDetailActivity;
import com.luboganev.carbrands.carbrands.CarBrandListDisplayModel;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public class Navigator {
    private final Activity mActivity;

    public Navigator(Activity activity) {
        mActivity = activity;
    }

    private static final String CARBRAND_LIST_DISPLAYMODEL_EXTRA = "carbrand_list_displaymodel_extra";

    public void navigateToBrandDetail(CarBrandListDisplayModel listDisplayModel) {
        Intent intent = new Intent(mActivity.getApplicationContext(), CarBrandDetailActivity.class);
        intent.putExtra(CARBRAND_LIST_DISPLAYMODEL_EXTRA, listDisplayModel);
        mActivity.startActivity(intent);
    }

    public CarBrandListDisplayModel getCarBrandListDisplayModel(Bundle extras) {
        if (extras != null) {
            Parcelable extra = extras.getParcelable(CARBRAND_LIST_DISPLAYMODEL_EXTRA);
            if (extra != null && extra instanceof CarBrandListDisplayModel) {
                return (CarBrandListDisplayModel) extra;
            }
        }
        return null;
    }
}
