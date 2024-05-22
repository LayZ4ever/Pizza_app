package com.example.pizzaapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pizzaapp.ingredients.Cheese;
import com.example.pizzaapp.ingredients.Ingredient;
import com.example.pizzaapp.ingredients.Meat;
import com.example.pizzaapp.ingredients.Sauce;
import com.example.pizzaapp.ingredients.Vegetable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PizzaAdapter pizzaAdapter;
    private List<Pizza> pizzaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pizzeria");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        pizzaList = new ArrayList<>();
        pizzaList.add(new Pizza("Margherita", Arrays.asList(
                tomatoSauce,
                mozzarella,
                basil
        )));
        pizzaList.add(new Pizza("Pepperoni", Arrays.asList(
                tomatoSauce,
                mozzarella,
                pepperoni
        )));
        pizzaList.add(new Pizza("Hawaiian", Arrays.asList(
                tomatoSauce,
                mozzarella,
                ham,
                pineapple
        )));
        pizzaList.add(new Pizza("BBQ Chicken", Arrays.asList(
                bbqSauce,
                mozzarella,
                chicken,
                onions
        )));
        pizzaList.add(new Pizza("Veggie", Arrays.asList(
                tomatoSauce,
                mozzarella,
                bellPeppers,
                onions,
                olives
        )));
        pizzaList.add(new Pizza("Four Cheese", Arrays.asList(
                tomatoSauce,
                mozzarella,
                parmesan,
                gorgonzola,
                goatCheese
        )));
        pizzaList.add(new Pizza("Meat Lover's", Arrays.asList(
                tomatoSauce,
                mozzarella,
                pepperoni,
                sausage,
                bacon,
                ham
        )));
        pizzaList.add(new Pizza("Supreme", Arrays.asList(
                tomatoSauce,
                mozzarella,
                pepperoni,
                sausage,
                bellPeppers,
                onions,
                olives,
                mushrooms
        )));
        pizzaList.add(new Pizza("Buffalo Chicken", Arrays.asList(
                buffaloSauce,
                mozzarella,
                chicken,
                onions
        )));
        pizzaList.add(new Pizza("Mushroom", Arrays.asList(
                tomatoSauce,
                mozzarella,
                mushrooms,
                garlic
        )));

        pizzaAdapter = new PizzaAdapter(pizzaList);
        recyclerView.setAdapter(pizzaAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            // Handle cart icon click
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}