package com.luboganev.carbrands.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.luboganev.carbrands.carbrandDetail.CarBrandDetailActivity;
import com.luboganev.carbrands.carbrands.CarBrandListDisplayModel;

/**
 * This class encapsulates the necessary boilerplate code for navigating from one activity to another.
 *
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public class Navigator {
    private final Activity mActivity;

    public Navigator(Activity activity) {
        mActivity = activity;
    }

    private static final String CARBRAND_LIST_DISPLAYMODEL_EXTRA = "carbrand_list_displaymodel_extra";

    /**
     * Assuming that the current activity is the CarBrandsListActivity, this method starts the
     * CarBrandsDetailActivity and additionally sends it the display model of the selected CarBrand
     * item from the list.
     *
     * @param listDisplayModel
     */
    public void navigateToBrandDetail(CarBrandListDisplayModel listDisplayModel) {
        Intent intent = new Intent(mActivity.getApplicationContext(), CarBrandDetailActivity.class);
        intent.putExtra(CARBRAND_LIST_DISPLAYMODEL_EXTRA, listDisplayModel);
        mActivity.startActivity(intent);
    }

    /**
     * This method extracts from the input bundle the display model
     * of the selected CarBrand item from the CarBrands list.
     *
     * @param extras
     *      The extras of the launching Intent
     */
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
