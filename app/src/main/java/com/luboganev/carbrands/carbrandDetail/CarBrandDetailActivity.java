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
import com.luboganev.carbrands.common.NavigatorModule;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_brand_detail);
        ButterKnife.inject(this);
    }

    @Override
    protected List<Object> getActivityModules() {
        return Arrays.<Object>asList(new NavigatorModule(this), new CarBrandDetailModule(this));
    }

    @Override
    protected boolean shouldInjectSelf() {
        return true;
    }

    @Override
    protected void onInjected(Bundle savedInstanceState) {
        super.onInjected(savedInstanceState);
        presenter.onViewCreated(getIntent().getExtras(), savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewShown();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onViewSaveState(outState);
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
        countryOriginTextView.setText(carBrand.getCountryName());
        founderNamesTextView.setText(carBrand.getFoundersNames());
        Picasso.with(this).load(carBrand.getmLogoImageUrl()).placeholder(R.drawable.loading_placeholder).into(logoImageView);
    }
}
