package com.luboganev.carbrands.carbrandDetail;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.luboganev.carbrands.R;
import com.luboganev.carbrands.baseui.BaseDaggerActivity;
import com.luboganev.carbrands.carbrands.CarBrandListDisplayModel;
import com.luboganev.carbrands.common.CarBrandDetailIntentHelper;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CarBrandDetailActivity extends BaseDaggerActivity implements CarBrandDetailPresenterOutput {

    @Inject CarBrandDetailPresenterInput presenter;

    @InjectView(R.id.logoImageView) ImageView logoImageView;
    @InjectView(R.id.countryOriginTextView) TextView countryOriginTextView;
    @InjectView(R.id.founderNamesTextView) TextView founderNamesTextView;
    @InjectView(R.id.detailProgressBar) ProgressBar progressBar;
    @InjectView(R.id.contentScrollView) ScrollView contentScrollView;

    @Override
    protected Object[] getActivityModules() {
        CarBrandListDisplayModel listDisplayModel = CarBrandDetailIntentHelper.getCarBrandListDisplayModel(getIntent().getExtras());
        return new Object[]{new CarBrandDetailModule(listDisplayModel.getCarBrandId(), listDisplayModel.getName())};
    }

    @Override
    protected void onInjected(Bundle savedInstanceState) {
        super.onInjected(savedInstanceState);
        presenter.setView(this);
        setContentView(R.layout.activity_car_brand_detail);
        ButterKnife.inject(this);
    }

    @Override
    protected void onDestroyObjectGraph() {
        super.onDestroyObjectGraph();
        presenter.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewShow();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // CarBrandDetailPresenterOutput implementation

    @Override
    public void setCarBrandName(String carBrandName) {
        if(carBrandName == null) {
            setTitle(R.string.title_activity_car_brand_detail);
            return;
        }
        setTitle(carBrandName);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        contentScrollView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        contentScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCarBrand(CarBrandDetailDisplayModel carBrand) {
        setTitle(carBrand.getName());
        countryOriginTextView.setText(carBrand.countryName);
        founderNamesTextView.setText(carBrand.foundersNames);
        Picasso.with(this).load(carBrand.logoImageUrl).placeholder(R.drawable.loading_placeholder).into(logoImageView);
    }
}
