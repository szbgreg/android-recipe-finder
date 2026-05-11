package hu.nje.recipefinder.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.domain.Category;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryHolder>{

    private List<Category> categories;

    public CategoryListAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = categories.get(position);
        holder.categoryNameTextView.setText(category.getName());
        Glide.with(holder.itemView.getContext())
                .load(category.getImageUrl())
                .into(holder.categoryImageView);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        public ImageView categoryImageView;
        public TextView categoryNameTextView;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            categoryImageView = itemView.findViewById(R.id.categoryImageView);
            categoryNameTextView = itemView.findViewById(R.id.categoryNameTextView);
        }
    }
}
