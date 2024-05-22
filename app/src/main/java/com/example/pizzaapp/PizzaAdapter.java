package com.example.pizzaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private final List<Pizza> pizzaList;
    private final OnPizzaClickListener listener;

    public interface OnPizzaClickListener {
        void onPizzaClick(Pizza pizza);
    }

    public PizzaAdapter(List<Pizza> pizzaList, OnPizzaClickListener listener) {
        this.pizzaList = pizzaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.bind(pizza, listener);
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {

        private final TextView pizzaNameTextView;
        private final TextView pizzaIngredientsTextView;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaNameTextView = itemView.findViewById(R.id.pizza_name);
            pizzaIngredientsTextView = itemView.findViewById(R.id.pizza_ingredients);
        }

        public void bind(final Pizza pizza, final OnPizzaClickListener listener) {
            pizzaNameTextView.setText(pizza.getName());
            pizzaIngredientsTextView.setText(pizza.getIngredientsString());
            itemView.setOnClickListener(v -> listener.onPizzaClick(pizza));
        }
    }
}