package com.example.pizzaapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzaapp.ingredients.Ingredient;
import com.example.pizzaapp.utils.IngredientUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PizzaAdapter.OnPizzaClickListener {

    private RecyclerView recyclerView;
    private PizzaAdapter pizzaAdapter;
    private List<Pizza> pizzaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pizzaList = PizzaData.getPizzaList();
        pizzaAdapter = new PizzaAdapter(pizzaList, this);
        recyclerView.setAdapter(pizzaAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {
            // Handle cart icon click
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPizzaClick(Pizza pizza) {
        List<Ingredient> fullSauces = IngredientUtils.getFullSauces(pizzaList);
        List<Ingredient> fullCheeses = IngredientUtils.getFullCheeses(pizzaList);
        List<Ingredient> fullMeats = IngredientUtils.getFullMeats(pizzaList);
        List<Ingredient> fullVegetables = IngredientUtils.getFullVegetables(pizzaList);

        PizzaCustomizationBottomSheet customizationBottomSheet = PizzaCustomizationBottomSheet.newInstance(
                pizza, fullSauces, fullCheeses, fullMeats, fullVegetables);
        customizationBottomSheet.show(getSupportFragmentManager(), customizationBottomSheet.getTag());
    }
}