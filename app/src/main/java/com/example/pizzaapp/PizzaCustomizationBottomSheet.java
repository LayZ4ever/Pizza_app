package com.example.pizzaapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.pizzaapp.ingredients.Ingredient;
import com.example.pizzaapp.utils.SelectionUtils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PizzaCustomizationBottomSheet extends BottomSheetDialogFragment {

    private static final String ARG_PIZZA = "selected_pizza";
    private static final String ARG_SAUCES = "full_sauces";
    private static final String ARG_CHEESES = "full_cheeses";
    private static final String ARG_MEATS = "full_meats";
    private static final String ARG_VEGETABLES = "full_vegetables";

    private Pizza selectedPizza;
    private List<Ingredient> fullSauces;
    private List<Ingredient> fullCheeses;
    private List<Ingredient> fullMeats;
    private List<Ingredient> fullVegetables;
    private Button addToBasketButton;
    private double currentPrice;

    public static PizzaCustomizationBottomSheet newInstance(Pizza selectedPizza, List<Ingredient> fullSauces, List<Ingredient> fullCheeses, List<Ingredient> fullMeats, List<Ingredient> fullVegetables) {
        PizzaCustomizationBottomSheet fragment = new PizzaCustomizationBottomSheet();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PIZZA, selectedPizza);
        args.putParcelableArrayList(ARG_SAUCES, new ArrayList<>(fullSauces));
        args.putParcelableArrayList(ARG_CHEESES, new ArrayList<>(fullCheeses));
        args.putParcelableArrayList(ARG_MEATS, new ArrayList<>(fullMeats));
        args.putParcelableArrayList(ARG_VEGETABLES, new ArrayList<>(fullVegetables));
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza_customization, container, false);

        if (getArguments() != null) {
            selectedPizza = getArguments().getParcelable(ARG_PIZZA);
            fullSauces = getArguments().getParcelableArrayList(ARG_SAUCES);
            fullCheeses = getArguments().getParcelableArrayList(ARG_CHEESES);
            fullMeats = getArguments().getParcelableArrayList(ARG_MEATS);
            fullVegetables = getArguments().getParcelableArrayList(ARG_VEGETABLES);
        }

        TextView pizzaNameTextView = view.findViewById(R.id.pizza_name);
        TextView ingredientsTextView = view.findViewById(R.id.pizza_ingredients);
        addToBasketButton = view.findViewById(R.id.button_add_to_basket);
        pizzaNameTextView.setText(selectedPizza.getName());
        ingredientsTextView.setText(selectedPizza.getIngredientsString());

        currentPrice = selectedPizza.getMediumPrice();
        updateAddToBasketButton(currentPrice);

        // Size selection
        RadioGroup sizeGroup = view.findViewById(R.id.size_group);
        List<String> sizes = Arrays.asList("Small", "Medium", "Large");
        SelectionUtils.createAndSelectRadioButtons(getContext(), sizeGroup, sizes, "Medium");
        sizeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String size = ((RadioButton) view.findViewById(checkedId)).getText().toString();
            updatePriceBasedOnSize(size);
        });

        // Sauce selection
        RadioGroup sauceGroup = view.findViewById(R.id.sauce_group);
        SelectionUtils.createAndSelectRadioButtons(getContext(), sauceGroup, getIngredientNames(fullSauces), getDefaultSauce(selectedPizza));
        sauceGroup.setOnCheckedChangeListener((group, checkedId) -> updatePrice());

        // Cheese selection
        LinearLayout cheeseGroup = view.findViewById(R.id.cheese_group);
        SelectionUtils.createAndSelectCheckBoxes(getContext(), cheeseGroup, fullCheeses, extractDefaultIngredients(selectedPizza, "Cheese"));
        addCheckBoxListeners(cheeseGroup);

        // Meat selection
        LinearLayout meatGroup = view.findViewById(R.id.meat_group);
        SelectionUtils.createAndSelectCheckBoxes(getContext(), meatGroup, fullMeats, extractDefaultIngredients(selectedPizza, "Meat"));
        addCheckBoxListeners(meatGroup);

        // Vegetables selection
        LinearLayout vegetableGroup = view.findViewById(R.id.vegetable_group);
        SelectionUtils.createAndSelectCheckBoxes(getContext(), vegetableGroup, fullVegetables, extractDefaultIngredients(selectedPizza, "Vegetable"));
        addCheckBoxListeners(vegetableGroup);

        // Add to basket button
        addToBasketButton.setOnClickListener(v -> {
            // Handle adding to basket action here
            dismiss();
        });

        return view;
    }

    private List<String> getIngredientNames(List<Ingredient> ingredients) {
        List<String> names = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            names.add(ingredient.getName());
        }
        return names;
    }

    private String getDefaultSauce(Pizza pizza) {
        List<Ingredient> sauces = pizza.getIngredientsByType("Sauce");
        if (!sauces.isEmpty()) {
            return sauces.get(0).getName(); // Assuming the first sauce is the default
        }
        return "";
    }

    private List<String> extractDefaultIngredients(Pizza pizza, String type) {
        List<String> defaultIngredients = new ArrayList<>();
        for (Ingredient ingredient : pizza.getIngredientsByType(type)) {
            defaultIngredients.add(ingredient.getName().toLowerCase());
        }
        return defaultIngredients;
    }

    private void updatePriceBasedOnSize(String size) {
        switch (size) {
            case "Small":
                currentPrice = selectedPizza.getSmallPrice();
                break;
            case "Medium":
                currentPrice = selectedPizza.getMediumPrice();
                break;
            case "Large":
                currentPrice = selectedPizza.getLargePrice();
                break;
        }
        updatePrice();
    }

    private void updatePrice() {
        double newPrice = currentPrice;
        // Add $1 for each new ingredient different from the default ingredients
        newPrice += calculateExtraIngredientCost();
        updateAddToBasketButton(newPrice);
    }

    private double calculateExtraIngredientCost() {
        double extraCost = 0;
        // Calculate extra cost for sauces, cheeses, meats, and vegetables
        extraCost += getExtraCostForGroup(fullSauces, selectedPizza.getIngredientsByType("Sauce"));
        extraCost += getExtraCostForGroup(fullCheeses, selectedPizza.getIngredientsByType("Cheese"));
        extraCost += getExtraCostForGroup(fullMeats, selectedPizza.getIngredientsByType("Meat"));
        extraCost += getExtraCostForGroup(fullVegetables, selectedPizza.getIngredientsByType("Vegetable"));
        return extraCost;
    }

    private double getExtraCostForGroup(List<Ingredient> allIngredients, List<Ingredient> defaultIngredients) {
        double extraCost = 0;
        for (Ingredient ingredient : allIngredients) {
            CheckBox checkBox = getView().findViewWithTag(ingredient.getName());
            if (checkBox != null && checkBox.isChecked() && !defaultIngredients.contains(ingredient)) {
                extraCost += 1;
            }
        }
        return extraCost;
    }

    private void addCheckBoxListeners(LinearLayout group) {
        for (int i = 0; i < group.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) group.getChildAt(i);
            checkBox.setTag(checkBox.getText().toString());
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());
        }
    }

    private void updateAddToBasketButton(double newPrice) {
        addToBasketButton.setText(String.format("Add to basket: $%.2f", newPrice));
    }
}