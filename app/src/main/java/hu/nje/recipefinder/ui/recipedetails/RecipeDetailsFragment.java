package hu.nje.recipefinder.ui.recipedetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Button;

import hu.nje.recipefinder.data.local.database.AppDatabase;
import hu.nje.recipefinder.data.local.entity.FavoriteRecipeEntity;
import hu.nje.recipefinder.data.local.entity.ShoppingListItemEntity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hu.nje.recipefinder.R;

public class RecipeDetailsFragment extends Fragment {

    private RecipeDetailsViewModel viewModel;
    private String mealId;
    private ImageView detailImageView;
    private TextView detailNameTextView;
    private TextView categoryTextView;
    private TextView areaTextView;
    private TextView instructionsTextView;
    private ProgressBar loadingProgressBar;
    private NestedScrollView nestedScrollView;
    private IngredientListAdapter adapter;
    private ImageButton btnFavorite;
    private Button increaseButton;
    private Button decreaseButton;
    private TextView servingsCountTextView;
    private int servingsCount = 2;
    private Button addToShoppingListButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RecipeDetailsViewModel.class);

        if (getArguments() != null) {
            mealId = getArguments().getString("mealId");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        loadingProgressBar = view.findViewById(R.id.loadingProgressBar);
        detailNameTextView = view.findViewById(R.id.detailTextView);
        categoryTextView = view.findViewById(R.id.categoryTextView);
        areaTextView = view.findViewById(R.id.areaTextView);
        instructionsTextView = view.findViewById(R.id.instructionsTextView);
        detailImageView = view.findViewById(R.id.detailImageView);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);
        btnFavorite = view.findViewById(R.id.btnFavorite);
        increaseButton = view.findViewById(R.id.increaseButton);
        decreaseButton = view.findViewById(R.id.decreaseButton);
        servingsCountTextView = view.findViewById(R.id.servingsCountTextView);
        addToShoppingListButton = view.findViewById(R.id.addToShoppingListButton);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView(view);

        servingsCountTextView.setText(String.valueOf(servingsCount));

        increaseButton.setOnClickListener(v -> {
            servingsCount++;
            servingsCountTextView.setText(String.valueOf(servingsCount));
        });

        decreaseButton.setOnClickListener(v -> {
            if (servingsCount > 1) {
                servingsCount--;
                servingsCountTextView.setText(String.valueOf(servingsCount));
            }
        });

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
                    loadingProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                    nestedScrollView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
                }
        );

        viewModel.getRecipe().observe(getViewLifecycleOwner(), recipe -> {

            detailNameTextView.setText(recipe.getName());
            categoryTextView.setText(recipe.getCategory());
            areaTextView.setText(recipe.getArea());
            instructionsTextView.setText(recipe.getInstructions());
            adapter.setIngredients(recipe.getIngredients());

            Glide.with(this)
                    .load(recipe.getImageUrl())
                    .into(detailImageView);

            btnFavorite.setOnClickListener(v -> {
                FavoriteRecipeEntity favorite = new FavoriteRecipeEntity(
                        recipe.getId(),
                        recipe.getName(),
                        recipe.getImageUrl(),
                        recipe.getCategory()
                );

                AppDatabase.getInstance(requireContext())
                        .favoriteRecipeDao()
                        .insert(favorite);

                Toast.makeText(requireContext(),
                        "Kedvencekhez adva",
                        Toast.LENGTH_SHORT).show();
            });

            addToShoppingListButton.setOnClickListener(v -> {
                for (int i = 0; i < recipe.getIngredients().size(); i++) {
                    ShoppingListItemEntity item = new ShoppingListItemEntity(
                            recipe.getName(),
                            recipe.getIngredients().get(i).getName(),
                            recipe.getIngredients().get(i).getMeasure(),
                            servingsCount
                    );

                    AppDatabase.getInstance(requireContext())
                            .shoppingListDao()
                            .insert(item);
                }

                Toast.makeText(requireContext(),
                        "Bevásárló listához adva",
                        Toast.LENGTH_SHORT).show();
            });
        });

        if (mealId != null) {
            viewModel.loadRecipe(mealId);
        }
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.ingredientsRecyclerView);
        adapter = new IngredientListAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}