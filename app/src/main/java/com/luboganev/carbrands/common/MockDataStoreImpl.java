package com.luboganev.carbrands.common;

import android.os.AsyncTask;

import com.luboganev.carbrands.model.CarBrand;
import com.luboganev.carbrands.model.CarBrandFounder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of a data store filled with mock data stored in memory.
 * Any delays while retrieving the data are simulated through a sleeping AsyncTask.
 *
 * Created by Lyubomir Ganev (ganevlyu) on 21.04.2015
 */
public class MockDataStoreImpl implements DataStore {

    private long mFilterCarBrandId = CarBrand.STORE_ID_NONE;
    private String mFilterCountryCode = null;
    private WeakReference<DataStoreCallback> mCallback = null;

    // DataStore
    @Override
    public void resetFilters() {
        mFilterCarBrandId = CarBrand.STORE_ID_NONE;
        mFilterCountryCode = null;
    }

    @Override
    public void filterCountryCode(String countryCode) {
        mFilterCountryCode = countryCode;
    }

    @Override
    public void filterCarBrandId(long id) {
        mFilterCarBrandId = id;
    }

    @Override
    public void execute(DataStoreCallback callback) {
        mCallback = new WeakReference<>(callback);

        if (mTask.getStatus() == AsyncTask.Status.RUNNING) {
            return;
        }

        if (mTask.getStatus() == AsyncTask.Status.FINISHED) {
            mTask = new SlowDataStoreTask();
        }

        mTask.execute();
    }

    // Simulated slow data loading

    private class SlowDataStoreTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            DataStoreCallback callback = mCallback.get();
            if (callback != null) {

                List<CarBrand> resultSet = lazyInitStoredCarBrands();

                if(mFilterCarBrandId != CarBrand.STORE_ID_NONE) {
                    resultSet = filterId(mFilterCarBrandId, resultSet);
                }

                if(mFilterCountryCode != null) {
                    resultSet = filterCountryCode(mFilterCountryCode, resultSet);
                }

                callback.foundCarBrands(resultSet);
            }
            mCallback = null;
        }
    }

    private SlowDataStoreTask mTask = new SlowDataStoreTask();

    // Helper methods for filtering the data

    private static List<CarBrand> filterId(long id, List<CarBrand> carBrands) {
        List<CarBrand> result = new ArrayList<>();
        for(CarBrand brand : carBrands) {
            if(brand.id == id) {
                result.add(brand);
                break;
            }
        }
        return result;
    }

    private static List<CarBrand> filterCountryCode(String countryCode, List<CarBrand> carBrands) {
        List<CarBrand> result = new ArrayList<>();
        for(CarBrand brand : carBrands) {
            if(brand.countryCode.equals(countryCode)) {
                result.add(brand);
            }
        }
        return result;
    }

    // Mock data initialization

    private List<CarBrand> lazyInitStoredCarBrands() {
        if (mStoredCarBrands.isEmpty()) {
            initializeDummyData();
        }
        return mStoredCarBrands;
    }

    private final List<CarBrand> mStoredCarBrands = new ArrayList<>();

    private void initializeDummyData() {
        long id = 1;

        CarBrand brand = new CarBrand("Alfa Romeo", "IT", "http://upload.wikimedia.org/wikipedia/en/thumb/2/24/Alfa_Romeo.svg/500px-Alfa_Romeo.svg.png");
        brand.id = id++;
        CarBrandFounder founder = new CarBrandFounder("Alexandre", "Darracq");
        brand.founders.add(founder);
        founder = new CarBrandFounder("Ugo", "Stella");
        brand.founders.add(founder);
        founder = new CarBrandFounder("Nicola", "Romeo");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Aston Martin", "GB", "http://upload.wikimedia.org/wikipedia/en/thumb/e/e9/AstonMartinLogo.svg/500px-AstonMartinLogo.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Lionel", "Martin");
        brand.founders.add(founder);
        founder = new CarBrandFounder("Robert", "Bamford");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Audi", "DE", "http://upload.wikimedia.org/wikipedia/en/0/04/Audi_AG_logo.png");
        brand.id = id++;
        founder = new CarBrandFounder("August", "Horch");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Bentley", "GB", "http://upload.wikimedia.org/wikipedia/en/thumb/6/6c/Bentley_logo.svg/500px-Bentley_logo.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("H. M.", "Bentley");
        brand.founders.add(founder);
        founder = new CarBrandFounder("W. O.", "Bentley");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("BMW", "DE", "http://upload.wikimedia.org/wikipedia/commons/thumb/4/44/BMW.svg/500px-BMW.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Franz Josef", "Popp");
        brand.founders.add(founder);
        founder = new CarBrandFounder("Karl", "Rapp");
        brand.founders.add(founder);
        founder = new CarBrandFounder("Camillo", "Castiglioni");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Bugatti", "FR", "http://upload.wikimedia.org/wikipedia/en/thumb/6/60/Bugatti_logo.svg/500px-Bugatti_logo.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Wolfgang", "Dürheimer");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Ferrari", "IT", "http://upload.wikimedia.org/wikipedia/en/thumb/d/d1/Ferrari-Logo.svg/500px-Ferrari-Logo.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Enzo", "Ferrari");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Fiat", "IT", "http://upload.wikimedia.org/wikipedia/en/thumb/9/96/Fiat_Logo.svg/500px-Fiat_Logo.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Giovanni", "Agnelli");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Ford", "US", "http://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Ford_Motor_Company_Logo.svg/500px-Ford_Motor_Company_Logo.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Henry", "Ford");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Lamborghini", "IT", "http://upload.wikimedia.org/wikipedia/en/thumb/d/df/Lamborghini_Logo.svg/500px-Lamborghini_Logo.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Ferruccio", "Lamborghini");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Lexus", "JP", "http://upload.wikimedia.org/wikipedia/en/thumb/d/d1/Lexus_division_emblem.svg/500px-Lexus_division_emblem.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Eiji", "Toyoda");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Mercedes-Benz", "DE", "http://upload.wikimedia.org/wikipedia/en/b/bb/Mercedes_benz_silverlogo.png");
        brand.id = id++;
        founder = new CarBrandFounder("Karl", "Benz");
        brand.founders.add(founder);
        founder = new CarBrandFounder("Gottlieb", "Daimler");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Mitsubishi", "JP", "http://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Mitsubishi_logo.svg/500px-Mitsubishi_logo.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Iwasaki", "Yatarō");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Opel", "DE", "http://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Opel-Logo-2011-Slogan-Vector.svg/500px-Opel-Logo-2011-Slogan-Vector.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Adam", "Opel");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Porsche", "DE", "http://upload.wikimedia.org/wikipedia/en/7/73/Porsche_logotype.png");
        brand.id = id++;
        founder = new CarBrandFounder("Ferdinand", "Porsche");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Subaru", "JP", "http://upload.wikimedia.org/wikipedia/en/thumb/4/47/Subaru_logo.svg/500px-Subaru_logo.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Kenji", "Kita");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Toyota", "JP", "http://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Toyota_carlogo.svg/500px-Toyota_carlogo.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("Kiichiro", "Toyoda");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Volkswagen", "DE", "http://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Volkswagen_logo_2012.svg/500px-Volkswagen_logo_2012.svg.png");
        brand.id = id++;
        founder = new CarBrandFounder("German Labour Front", "");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
        

        brand = new CarBrand("Volvo", "SE", "http://upload.wikimedia.org/wikipedia/en/9/9c/Volvo_Cars_logo.png");
        brand.id = id++;
        founder = new CarBrandFounder("Gustav", "Larson");
        brand.founders.add(founder);
        founder = new CarBrandFounder("Assar", "Gabrielsson");
        brand.founders.add(founder);
        mStoredCarBrands.add(brand);
    }
}