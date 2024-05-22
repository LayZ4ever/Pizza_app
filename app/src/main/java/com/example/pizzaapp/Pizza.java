package com.example.pizzaapp;

import com.example.pizzaapp.ingredients.Ingredient;
import java.util.List;

public class Pizza {
    private String name;
    private List<Ingredient> ingredients;

    public Pizza(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
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