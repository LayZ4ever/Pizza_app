package com.example.pizzaapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.pizzaapp.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pizza implements Parcelable {
    private String name;
    private List<Ingredient> ingredients;
    private double smallPrice;
    private double mediumPrice;
    private double largePrice;

    public Pizza(String name, List<Ingredient> ingredients, double smallPrice, double mediumPrice, double largePrice) {
        this.name = name;
        this.ingredients = ingredients;
        this.smallPrice = smallPrice;
        this.mediumPrice = mediumPrice;
        this.largePrice = largePrice;
    }

    protected Pizza(Parcel in) {
        name = in.readString();
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
        smallPrice = in.readDouble();
        mediumPrice = in.readDouble();
        largePrice = in.readDouble();
    }

    public static final Creator<Pizza> CREATOR = new Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        @Override
        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getIngredientsString() {
        return ingredients.stream().map(Ingredient::getName).collect(Collectors.joining(", "));
    }

    public double getSmallPrice() {
        return smallPrice;
    }

    public double getMediumPrice() {
        return mediumPrice;
    }

    public double getLargePrice() {
        return largePrice;
    }

    public List<Ingredient> getIngredientsByType(String type) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeTypedList(ingredients);
        parcel.writeDouble(smallPrice);
        parcel.writeDouble(mediumPrice);
        parcel.writeDouble(largePrice);
    }
}