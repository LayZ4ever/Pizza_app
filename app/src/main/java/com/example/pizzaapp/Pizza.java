package com.example.pizzaapp;

import com.example.pizzaapp.ingredients.Ingredient;
import java.util.List;

public class Pizza {
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

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
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

    public String getIngredientsString() {
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(ingredient.getName());
        }
        return sb.toString();
    }
}