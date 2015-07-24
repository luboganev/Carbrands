package com.luboganev.carbrands.carbrandDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.luboganev.carbrands.carbrands.CarBrandListDisplayModel;
import com.luboganev.carbrands.common.CountryNamesHelper;
import com.luboganev.carbrands.model.CarBrand;
import com.luboganev.carbrands.model.CarBrandFounder;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 20.04.2015
 */
public class CarBrandDetailDisplayModel extends CarBrandListDisplayModel implements Parcelable {
    private final String mCountryName;
    private final String mLogoImageUrl;
    private final String mFoundersNames;

    public String getCountryName() {
        return mCountryName;
    }

    public String getmLogoImageUrl() {
        return mLogoImageUrl;
    }

    public String getFoundersNames() {
        return mFoundersNames;
    }

    public CarBrandDetailDisplayModel(CarBrand carBrand) {
        super(carBrand);
        mCountryName = CountryNamesHelper.getCountryName(carBrand.getCountryCode());
        mLogoImageUrl = carBrand.getLogoImageUrl();

        StringBuilder sb = new StringBuilder();
        for (CarBrandFounder carBrandFounder : carBrand.getFounders()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(carBrandFounder.getFirstName()).append(" ").append(carBrandFounder.getLastName());
        }
        mFoundersNames = sb.toString();
    }

    protected CarBrandDetailDisplayModel(Parcel in) {
        super(in);
        mCountryName = in.readString();
        mLogoImageUrl = in.readString();
        mFoundersNames = in.readString();
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mCountryName);
        dest.writeString(mLogoImageUrl);
        dest.writeString(mFoundersNames);
    }

    @SuppressWarnings("unused")
    public static final Creator<CarBrandDetailDisplayModel> CREATOR = new Creator<CarBrandDetailDisplayModel>() {
        @Override
        public CarBrandDetailDisplayModel createFromParcel(Parcel in) {
            return new CarBrandDetailDisplayModel(in);
        }

        @Override
        public CarBrandDetailDisplayModel[] newArray(int size) {
            return new CarBrandDetailDisplayModel[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        // if the supers are not equal then we do not care for the extra fields here
        if (!super.equals(o)) {
            return false;
        }

        // Return false if the other object has the wrong type.
        // This type may be an interface depending on the interface's specification.
        if (!(o instanceof CarBrandDetailDisplayModel)) {
            return false;
        }

        // Cast to the appropriate type.
        // This will succeed because of the instanceof, and lets us access private fields.
        CarBrandDetailDisplayModel castedObject = (CarBrandDetailDisplayModel) o;

        // Check each field. Primitive fields, reference fields, and nullable reference
        // fields are all treated differently.
        return (mCountryName == null ? castedObject.mCountryName == null : mCountryName.equals(castedObject.mCountryName)) &&
                (mLogoImageUrl == null ? castedObject.mLogoImageUrl == null : mLogoImageUrl.equals(castedObject.mLogoImageUrl)) &&
                (mFoundersNames == null ? castedObject.mFoundersNames == null : mFoundersNames.equals(castedObject.mFoundersNames));
    }
}
