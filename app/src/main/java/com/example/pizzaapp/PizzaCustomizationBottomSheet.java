package com.example.pizzaapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.radiobutton.MaterialRadioButton;

public class PizzaCustomizationBottomSheet extends BottomSheetDialogFragment {

    private static final String ARG_PIZZA_NAME = "pizza_name";
    private static final String ARG_INGREDIENTS = "ingredients";

    private String pizzaName;
    private String ingredients;

    public static PizzaCustomizationBottomSheet newInstance(String pizzaName, String ingredients) {
        PizzaCustomizationBottomSheet fragment = new PizzaCustomizationBottomSheet();
        Bundle args = new Bundle();
        args.putString(ARG_PIZZA_NAME, pizzaName);
        args.putString(ARG_INGREDIENTS, ingredients);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza_customization, container, false);

        if (getArguments() != null) {
            pizzaName = getArguments().getString(ARG_PIZZA_NAME);
            ingredients = getArguments().getString(ARG_INGREDIENTS);
        }

        TextView pizzaNameTextView = view.findViewById(R.id.pizza_name);
        TextView ingredientsTextView = view.findViewById(R.id.pizza_ingredients);
        pizzaNameTextView.setText(pizzaName);
        ingredientsTextView.setText(ingredients);

        // Size selection
        RadioGroup sizeGroup = view.findViewById(R.id.size_group);
        sizeGroup.addView(createRadioButton("Small"));
        sizeGroup.addView(createRadioButton("Medium"));
        sizeGroup.addView(createRadioButton("Large"));

        // Sauce selection
        RadioGroup sauceGroup = view.findViewById(R.id.sauce_group);
        sauceGroup.addView(createRadioButton("Tomato Sauce"));
        sauceGroup.addView(createRadioButton("BBQ Sauce"));
        sauceGroup.addView(createRadioButton("Alfredo Sauce"));
        sauceGroup.addView(createRadioButton("Pesto Sauce"));
        sauceGroup.addView(createRadioButton("Buffalo Sauce"));

        // Cheese selection
        LinearLayout cheeseGroup = view.findViewById(R.id.cheese_group);
        cheeseGroup.addView(createCheckBox("Mozzarella"));
        cheeseGroup.addView(createCheckBox("Cheddar"));
        cheeseGroup.addView(createCheckBox("Parmesan"));
        cheeseGroup.addView(createCheckBox("Gorgonzola"));
        cheeseGroup.addView(createCheckBox("Goat Cheese"));

        // Meat selection
        LinearLayout meatGroup = view.findViewById(R.id.meat_group);
        meatGroup.addView(createCheckBox("Pepperoni"));
        meatGroup.addView(createCheckBox("Sausage"));
        meatGroup.addView(createCheckBox("Bacon"));
        meatGroup.addView(createCheckBox("Ham"));
        meatGroup.addView(createCheckBox("Chicken"));

        // Vegetables selection
        LinearLayout vegetableGroup = view.findViewById(R.id.vegetable_group);
        vegetableGroup.addView(createCheckBox("Bell Peppers"));
        vegetableGroup.addView(createCheckBox("Onions"));
        vegetableGroup.addView(createCheckBox("Olives"));
        vegetableGroup.addView(createCheckBox("Mushrooms"));
        vegetableGroup.addView(createCheckBox("Garlic"));
        vegetableGroup.addView(createCheckBox("Spinach"));
        vegetableGroup.addView(createCheckBox("Tomatoes"));

        // Confirm button
        view.findViewById(R.id.button_confirm).setOnClickListener(v -> {
            // Handle the confirmation action here
            dismiss();
        });

        return view;
    }

    private RadioButton createRadioButton(String text) {
        RadioButton radioButton = new MaterialRadioButton(getContext());
        radioButton.setText(text);
        return radioButton;
    }

    private CheckBox createCheckBox(String text) {
        CheckBox checkBox = new CheckBox(getContext());
        checkBox.setText(text);
        return checkBox;
    }
}