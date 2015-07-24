package com.luboganev.carbrands.common;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public class MockLocationManagerImpl implements LocationManager {
    private WeakReference<LocationManagerCallback> mLocationCallback;

    private class LocationFinderTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
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
