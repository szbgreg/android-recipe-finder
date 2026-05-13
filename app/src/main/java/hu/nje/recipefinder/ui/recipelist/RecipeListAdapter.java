package hu.nje.recipefinder.ui.recipelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.domain.Recipe;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeHolder> {

    private List<Recipe> recipes = new ArrayList<>();
    private NavController navController;

    public RecipeListAdapter(NavController navController) {
        this.navController = navController;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_recipe, parent, false);
        return new RecipeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.recipeNameTextView.setText(recipe.getName());
        Glide.with(holder.itemView.getContext())
                .load(recipe.getImageUrl())
                .into(holder.recipeImageImageView);

        holder.recipeImageImageView.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("mealId", recipe.getId());

            navController.navigate(R.id.action_list_to_details, args);
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class RecipeHolder extends RecyclerView.ViewHolder {

        public ImageView recipeImageImageView;
        public TextView recipeNameTextView;

        public RecipeHolder(@NonNull View itemView) {
            super(itemView);

            recipeImageImageView = itemView.findViewById(R.id.recipeImageImageView);
            recipeNameTextView = itemView.findViewById(R.id.recipeNameTextView);
        }
    }
}
