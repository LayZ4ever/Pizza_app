package com.example.pizzaapp.utils;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.pizzaapp.ingredients.Ingredient;
import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.List;

public class SelectionUtils {

    public static void createAndSelectRadioButtons(Context context, RadioGroup group, List<String> options, String defaultOption) {
        for (String option : options) {
            RadioButton radioButton = new MaterialRadioButton(context);
            radioButton.setText(option);
            group.addView(radioButton);
            if (option.equalsIgnoreCase(defaultOption)) {
                radioButton.setChecked(true);
            }
        }
    }

    public static void createAndSelectCheckBoxes(Context context, LinearLayout group, List<Ingredient> options, List<String> defaultOptions) {
        for (Ingredient option : options) {
            CheckBox checkBox = new CheckBox(context);
            checkBox.setText(option.getName());
            group.addView(checkBox);
            if (defaultOptions.contains(option.getName().toLowerCase())) {
                checkBox.setChecked(true);
            }
        }
    }
}