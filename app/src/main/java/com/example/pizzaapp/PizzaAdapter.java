package com.example.pizzaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {
    private List<Pizza> pizzaList;

    public PizzaAdapter(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.pizzaName.setText(pizza.getName());
        holder.pizzaIngredients.setText(pizza.getIngredientsString());
        holder.pizzaPrices.setText(String.format("Small: $%.2f | Medium: $%.2f | Large: $%.2f",
                pizza.getSmallPrice(), pizza.getMediumPrice(), pizza.getLargePrice()));

        holder.itemView.setOnClickListener(v -> {
            PizzaCustomizationBottomSheet bottomSheet = PizzaCustomizationBottomSheet.newInstance(
                    pizza.getName(), pizza.getIngredientsString());
            bottomSheet.show(((FragmentActivity) v.getContext()).getSupportFragmentManager(), bottomSheet.getTag());
        });
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView pizzaName, pizzaIngredients, pizzaPrices;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaName = itemView.findViewById(R.id.pizza_name);
            pizzaIngredients = itemView.findViewById(R.id.pizza_ingredients);
            pizzaPrices = itemView.findViewById(R.id.pizza_prices);
        }
    }
}