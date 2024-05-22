package com.example.pizzaapp;

import com.example.pizzaapp.ingredients.Cheese;
import com.example.pizzaapp.ingredients.Ingredient;
import com.example.pizzaapp.ingredients.Meat;
import com.example.pizzaapp.ingredients.Sauce;
import com.example.pizzaapp.ingredients.Vegetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PizzaData {

    public static List<Pizza> getPizzaList() {
        Ingredient tomatoSauce = new Sauce("Tomato sauce");
        Ingredient bbqSauce = new Sauce("BBQ sauce");
        Ingredient buffaloSauce = new Sauce("Buffalo sauce");
        Ingredient mozzarella = new Cheese("Mozzarella");
        Ingredient parmesan = new Cheese("Parmesan");
        Ingredient gorgonzola = new Cheese("Gorgonzola");
        Ingredient goatCheese = new Cheese("Goat cheese");
        Ingredient pepperoni = new Meat("Pepperoni");
        Ingredient sausage = new Meat("Sausage");
        Ingredient bacon = new Meat("Bacon");
        Ingredient ham = new Meat("Ham");
        Ingredient chicken = new Meat("Chicken");
        Ingredient basil = new Vegetable("Basil");
        Ingredient pineapple = new Vegetable("Pineapple");
        Ingredient onions = new Vegetable("Onions");
        Ingredient bellPeppers = new Vegetable("Bell peppers");
        Ingredient olives = new Vegetable("Olives");
        Ingredient mushrooms = new Vegetable("Mushrooms");
        Ingredient garlic = new Vegetable("Garlic");

        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(new Pizza("Margherita", Arrays.asList(
                tomatoSauce,
                mozzarella,
                basil
        ), 5.99, 7.99, 9.99));
        pizzaList.add(new Pizza("Pepperoni", Arrays.asList(
                tomatoSauce,
                mozzarella,
                pepperoni
        ), 6.99, 8.99, 10.99));
        pizzaList.add(new Pizza("Hawaiian", Arrays.asList(
                tomatoSauce,
                mozzarella,
                ham,
                pineapple
        ), 7.99, 9.99, 11.99));
        pizzaList.add(new Pizza("BBQ Chicken", Arrays.asList(
                bbqSauce,
                mozzarella,
                chicken,
                onions
        ), 8.99, 10.99, 12.99));
        pizzaList.add(new Pizza("Veggie", Arrays.asList(
                tomatoSauce,
                mozzarella,
                bellPeppers,
                onions,
                olives
        ), 6.99, 8.99, 10.99));
        pizzaList.add(new Pizza("Four Cheese", Arrays.asList(
                tomatoSauce,
                mozzarella,
                parmesan,
                gorgonzola,
                goatCheese
        ), 8.99, 10.99, 12.99));
        pizzaList.add(new Pizza("Meat Lover's", Arrays.asList(
                tomatoSauce,
                mozzarella,
                pepperoni,
                sausage,
                bacon,
                ham
        ), 9.99, 11.99, 13.99));
        pizzaList.add(new Pizza("Supreme", Arrays.asList(
                tomatoSauce,
                mozzarella,
                pepperoni,
                sausage,
                bellPeppers,
                onions,
                olives,
                mushrooms
        ), 9.99, 11.99, 13.99));
        pizzaList.add(new Pizza("Buffalo Chicken", Arrays.asList(
                buffaloSauce,
                mozzarella,
                chicken,
                onions
        ), 8.99, 10.99, 12.99));
        pizzaList.add(new Pizza("Mushroom", Arrays.asList(
                tomatoSauce,
                mozzarella,
                mushrooms,
                garlic
        ), 6.99, 8.99, 10.99));

        return pizzaList;
    }
}