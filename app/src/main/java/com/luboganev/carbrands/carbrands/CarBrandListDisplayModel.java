package com.luboganev.carbrands.carbrands;

import android.os.Parcel;
import android.os.Parcelable;

import com.luboganev.carbrands.model.CarBrand;

/**
 * Created by Lyubomir Ganev (ganevlyu) on 20.04.2015
 */
public class CarBrandListDisplayModel implements Parcelable {
    public final long id;
    public final String name;

    public long getCarBrandId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CarBrandListDisplayModel(CarBrand carBrand) {
        id = carBrand.id;
        name = carBrand.name;
    }

    protected CarBrandListDisplayModel(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Creator<CarBrandListDisplayModel> CREATOR = new Creator<CarBrandListDisplayModel>() {
        @Override
        public CarBrandListDisplayModel createFromParcel(Parcel in) {
            return new CarBrandListDisplayModel(in);
        }

        @Override
        public CarBrandListDisplayModel[] newArray(int size) {
            return new CarBrandListDisplayModel[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        // Return true if the objects are identical.
        // (This is just an optimization, not required for correctness.)
        if (this == o) {
            return true;
        }

        // Return false if the other object has the wrong type.
        // This type may be an interface depending on the interface's specification.
        if (!(o instanceof CarBrandListDisplayModel)) {
            return false;
        }

        // Cast to the appropriate type.
        // This will succeed because of the instanceof, and lets us access private fields.
        CarBrandListDisplayModel castedObject = (CarBrandListDisplayModel) o;

        // Check each field. Primitive fields, reference fields, and nullable reference
        // fields are all treated differently.
        return (name == null ? castedObject.name == null : name.equals(castedObject.name));
    }

    @Override
    public String toString() {
        return name;
    }
}
