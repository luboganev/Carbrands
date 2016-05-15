package com.luboganev.carbrands.common;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * A mock implementation of a location manager which picks a random country from a static list
 * and returns it as the current location on every request. Any delays while retrieving the
 * data are simulated through a sleeping AsyncTask.
 *
 * Created by luboganev on 21/04/2015
 */
public class MockLocationManagerImpl implements LocationManager {
    private WeakReference<LocationManagerCallback> mLocationCallback;

    private class LocationFinderTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (isCancelled()) {
                return null;
            }
            return CountryNamesHelper.getRandomCountryCode();
        }

        @Override
        protected void onPostExecute(String s) {
            LocationManagerCallback callback = mLocationCallback.get();
            if (callback != null) {
                callback.currentCountryCode(s);
            }
        }
    }

    private LocationFinderTask mTask = new LocationFinderTask();

    @Override
    public void requestUpdateCountryLocation(LocationManagerCallback callback) {
        mLocationCallback = new WeakReference<>(callback);

        if (mTask.getStatus() == AsyncTask.Status.RUNNING) {
            return;
        }

        if (mTask.getStatus() == AsyncTask.Status.FINISHED) {
            mTask = new LocationFinderTask();
        }

        mTask.execute();
    }
}
