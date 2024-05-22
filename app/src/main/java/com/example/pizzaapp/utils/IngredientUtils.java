package com.example.pizzaapp.utils;

import com.example.pizzaapp.ingredients.Ingredient;
import com.example.pizzaapp.Pizza;

import java.util.ArrayList;
import java.util.List;

public class IngredientUtils {

    public static List<Ingredient> getFullSauces(List<Pizza> pizzaList) {
        return getFullIngredientsByType(pizzaList, "Sauce");
    }

    public static List<Ingredient> getFullCheeses(List<Pizza> pizzaList) {
        return getFullIngredientsByType(pizzaList, "Cheese");
    }

    public static List<Ingredient> getFullMeats(List<Pizza> pizzaList) {
        return getFullIngredientsByType(pizzaList, "Meat");
    }

    public static List<Ingredient> getFullVegetables(List<Pizza> pizzaList) {
        return getFullIngredientsByType(pizzaList, "Vegetable");
    }

    private static List<Ingredient> getFullIngredientsByType(List<Pizza> pizzaList, String type) {
        List<Ingredient> fullIngredients = new ArrayList<>();
        for (Pizza pizza : pizzaList) {
            for (Ingredient ingredient : pizza.getIngredients()) {
                if (ingredient.getType().equalsIgnoreCase(type) && !containsIngredient(fullIngredients, ingredient)) {
                    fullIngredients.add(ingredient);
                }
            }
        }
        return fullIngredients;
    }

    private static boolean containsIngredient(List<Ingredient> ingredients, Ingredient ingredient) {
        for (Ingredient ingrain : ingredients) {
            if (ingrain.getName().equalsIgnoreCase(ingredient.getName())) {
                return true;
            }
        }
        return false;
    }
}