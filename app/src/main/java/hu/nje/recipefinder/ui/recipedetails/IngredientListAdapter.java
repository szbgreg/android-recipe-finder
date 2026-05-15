package hu.nje.recipefinder.ui.recipedetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.domain.Ingredient;

import android.widget.Button;
import android.widget.Toast;

import hu.nje.recipefinder.data.local.database.AppDatabase;
import hu.nje.recipefinder.data.local.entity.ShoppingListItemEntity;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientHolder> {
    private List<Ingredient> ingredients = new ArrayList<>();

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_ingredient, parent, false);
        return new IngredientListAdapter.IngredientHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.ingredientNameTextView.setText(ingredient.getName());
        holder.measureTextView.setText(ingredient.getMeasure());
        holder.addToCartButton.setOnClickListener(v -> {

            ShoppingListItemEntity item = new ShoppingListItemEntity(
                    "Custom ingredient",
                    ingredient.getName(),
                    ingredient.getMeasure(),
                    1
            );

            AppDatabase.getInstance(v.getContext())
                    .shoppingListDao()
                    .insert(item);

            Toast.makeText(
                    v.getContext(),
                    "Hozzávaló hozzáadva a kosárhoz!",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IngredientHolder extends RecyclerView.ViewHolder {
        private TextView ingredientNameTextView;
        private TextView measureTextView;
        private Button addToCartButton;

        public IngredientHolder(@NonNull View itemView) {
            super(itemView);
            ingredientNameTextView = itemView.findViewById(R.id.ingredientNameTextView);
            measureTextView = itemView.findViewById(R.id.measureTextView);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }
    }
}
