package com.example.pizzaapp.ingredients;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {
    private String name;
    private String type;

    public Ingredient(String name, String type) {
        this.name = name;
        this.type = type;
    }

    protected Ingredient(Parcel in) {
        name = in.readString();
        type = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(type);
    }
}