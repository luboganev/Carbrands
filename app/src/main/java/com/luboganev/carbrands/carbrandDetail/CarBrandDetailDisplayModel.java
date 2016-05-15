package com.luboganev.carbrands.carbrandDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.luboganev.carbrands.carbrands.CarBrandListDisplayModel;
import com.luboganev.carbrands.common.CountryNamesHelper;
import com.luboganev.carbrands.model.CarBrand;
import com.luboganev.carbrands.model.CarBrandFounder;

/**
 * Created by luboganev on 20/04/2015
 */
public class CarBrandDetailDisplayModel extends CarBrandListDisplayModel implements Parcelable {
    public final String countryName;
    public final String logoImageUrl;
    public final String foundersNames;

    public CarBrandDetailDisplayModel(CarBrand carBrand) {
        super(carBrand);
        countryName = CountryNamesHelper.getCountryName(carBrand.countryCode);
        logoImageUrl = carBrand.logoImageUrl;

        StringBuilder sb = new StringBuilder();
        for (CarBrandFounder carBrandFounder : carBrand.founders) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(carBrandFounder.firstName).append(" ").append(carBrandFounder.lastName);
        }
        foundersNames = sb.toString();
    }

    protected CarBrandDetailDisplayModel(Parcel in) {
        super(in);
        countryName = in.readString();
        logoImageUrl = in.readString();
        foundersNames = in.readString();
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(countryName);
        dest.writeString(logoImageUrl);
        dest.writeString(foundersNames);
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
        return (countryName == null ? castedObject.countryName == null : countryName.equals(castedObject.countryName)) &&
                (logoImageUrl == null ? castedObject.logoImageUrl == null : logoImageUrl.equals(castedObject.logoImageUrl)) &&
                (foundersNames == null ? castedObject.foundersNames == null : foundersNames.equals(castedObject.foundersNames));
    }
}
