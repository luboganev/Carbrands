package com.luboganev.carbrands.carbrands;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.luboganev.carbrands.R;
import com.luboganev.carbrands.baseui.BaseDaggerActivity;
import com.luboganev.carbrands.common.NavigatorModule;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CarBrandsListActivity extends BaseDaggerActivity implements CarBrandsPresenterOutput, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, CompoundButton.OnCheckedChangeListener {

    @Inject CarBrandsPresenterInput presenter;

    @InjectView(R.id.listView) ListView listView;
    @InjectView(R.id.progressBar) ProgressBar progressBar;
    @InjectView(R.id.listSwipeContainer)
    SwipeRefreshLayout swipeRefreshContainer;
    @InjectView(R.id.countryFilterCheckBox) CheckBox locationAwareFilteringCheckBox;

    private ArrayAdapter<CarBrandListDisplayModel> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_brands_list);
        ButterKnife.inject(this);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        swipeRefreshContainer.setOnRefreshListener(this);
        locationAwareFilteringCheckBox.setChecked(presenter.isLocationFilterActive());
        locationAwareFilteringCheckBox.setOnCheckedChangeListener(this);
    }

    @Override
    protected List<Object> getActivityModules() {
        return Arrays.<Object>asList(new NavigatorModule(this), new CarBrandsListModule(this));
    }

    @Override
    protected boolean shouldInjectSelf() {
        return true;
    }

    @Override
    protected void onInjected(Bundle savedInstanceState) {
        super.onInjected(savedInstanceState);
        presenter.onViewCreate(null, savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewShow();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onViewSaveState(outState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.carBrandClicked(mAdapter.getItem(position));
    }

    @Override
    public void onRefresh() {
        presenter.refreshRequested();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        presenter.filterChanged(isChecked);
    }

    // CarBrandsPresenterOutput

    @Override
    public void showProgress() {
        if(!swipeRefreshContainer.isRefreshing()) {
            progressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
        locationAwareFilteringCheckBox.setEnabled(false);
    }

    @Override
    public void hideProgress() {
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        swipeRefreshContainer.setRefreshing(false);
        locationAwareFilteringCheckBox.setEnabled(true);
    }

    @Override
    public void setCarBrands(List<CarBrandListDisplayModel> carBrands) {
        mAdapter.clear();
        mAdapter.addAll(carBrands);
    }

    @Override
    public void clearCarBrands() {
        mAdapter.clear();
    }

    @Override
    public void updateCountryName(String newCountryName) {
        setTitle(newCountryName);
    }

    @Override
    public void clearCountryName() {
        setTitle(R.string.title_activity_car_brands_list);
    }
}
