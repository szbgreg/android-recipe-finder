package hu.nje.recipefinder.ui.favorites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.data.local.entity.FavoriteRecipeEntity;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    public interface OnFavoriteClickListener {
        void onFavoriteClick(FavoriteRecipeEntity recipe);
    }

    private final List<FavoriteRecipeEntity> favorites = new ArrayList<>();
    private final OnFavoriteClickListener listener;

    public FavoritesAdapter(OnFavoriteClickListener listener) {
        this.listener = listener;
    }

    public void setFavorites(List<FavoriteRecipeEntity> newFavorites) {
        favorites.clear();
        favorites.addAll(newFavorites);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_recipe, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteRecipeEntity recipe = favorites.get(position);

        holder.nameTextView.setText(recipe.name);
        holder.categoryTextView.setText(recipe.category);

        Glide.with(holder.itemView.getContext())
                .load(recipe.thumbnail)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> listener.onFavoriteClick(recipe));
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView categoryTextView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.favoriteImageView);
            nameTextView = itemView.findViewById(R.id.favoriteNameTextView);
            categoryTextView = itemView.findViewById(R.id.favoriteCategoryTextView);
        }
    }
}