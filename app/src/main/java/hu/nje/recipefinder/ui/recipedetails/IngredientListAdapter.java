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
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IngredientHolder extends RecyclerView.ViewHolder {
        private TextView ingredientNameTextView;
        private TextView measureTextView;

        public IngredientHolder(@NonNull View itemView) {
            super(itemView);
            ingredientNameTextView = itemView.findViewById(R.id.ingredientNameTextView);
            measureTextView = itemView.findViewById(R.id.measureTextView);
        }
    }
}
