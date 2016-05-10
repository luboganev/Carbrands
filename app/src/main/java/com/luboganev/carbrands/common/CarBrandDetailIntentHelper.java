package com.luboganev.carbrands.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.luboganev.carbrands.carbrandDetail.CarBrandDetailActivity;
import com.luboganev.carbrands.carbrands.CarBrandListDisplayModel;

/**
 * Created by luboganev on 10/05/16.
 */
public class CarBrandDetailIntentHelper {
    private static final String CARBRAND_LIST_DISPLAYMODEL_EXTRA = "carbrand_list_displaymodel_extra";

    /**
     * Builds and Intent for starting the CarBrandsDetailActivity and
     * additionally sends it the display model of the selected CarBrand
     * item from the list.
     *
     * @param listDisplayModel
     */
    public static Intent getLaunchingIntent(Context context, CarBrandListDisplayModel listDisplayModel) {
        Intent intent = new Intent(context, CarBrandDetailActivity.class);
        intent.putExtra(CARBRAND_LIST_DISPLAYMODEL_EXTRA, listDisplayModel);
        return intent;
    }

    /**
     * This method extracts from the input bundle the display model
     * of the selected CarBrand item from the CarBrands list.
     *
     * @param extras
     *      The extras of the launching Intent
     */
    public static CarBrandListDisplayModel getCarBrandListDisplayModel(Bundle extras) {
        if (extras != null) {
            Parcelable extra = extras.getParcelable(CARBRAND_LIST_DISPLAYMODEL_EXTRA);
            if (extra != null && extra instanceof CarBrandListDisplayModel) {
                return (CarBrandListDisplayModel) extra;
            }
        }
        return null;
    }
}
