package hu.nje.recipefinder.ui.shoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.data.local.entity.ShoppingListItemEntity;
import android.widget.ImageButton;

import hu.nje.recipefinder.data.local.database.AppDatabase;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingViewHolder> {

    private final List<ShoppingListItemEntity> items = new ArrayList<>();

    public void setItems(List<ShoppingListItemEntity> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shopping_list, parent, false);
        return new ShoppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingViewHolder holder, int position) {

        ShoppingListItemEntity item = items.get(position);

        if (position == 0 || !item.recipeName.equals(items.get(position - 1).recipeName)) {
            holder.recipeNameTextView.setVisibility(View.VISIBLE);
            holder.recipeNameTextView.setText(item.recipeName);
        } else {
            holder.recipeNameTextView.setVisibility(View.GONE);
        }

        holder.deleteButton.setOnClickListener(v -> {
            int adapterPosition = holder.getBindingAdapterPosition();

            if (adapterPosition == RecyclerView.NO_POSITION) {
                return;
            }

            ShoppingListItemEntity itemToDelete = items.get(adapterPosition);

            AppDatabase.getInstance(v.getContext())
                    .shoppingListDao()
                    .deleteById(itemToDelete.id);

            items.remove(adapterPosition);
            notifyDataSetChanged();
        });

        holder.nameTextView.setText(item.ingredientName);
        holder.measureTextView.setText(item.measure);
        holder.servingsTextView.setText("x" + item.servings);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ShoppingViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView measureTextView;
        TextView servingsTextView;
        TextView recipeNameTextView;
        ImageButton deleteButton;

        public ShoppingViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.shoppingIngredientNameTextView);
            measureTextView = itemView.findViewById(R.id.shoppingMeasureTextView);
            servingsTextView = itemView.findViewById(R.id.shoppingServingsTextView);
            recipeNameTextView = itemView.findViewById(R.id.shoppingRecipeNameTextView);
            deleteButton = itemView.findViewById(R.id.deleteShoppingItemButton);
        }
    }
}