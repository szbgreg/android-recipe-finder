package hu.nje.recipefinder.ui.recipedetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.domain.Recipe;

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

            Glide.with(this)
                    .load(recipe.getImageUrl())
                    .into(detailImageView);
        });

        if (mealId != null) {
            viewModel.loadRecipe(mealId);
        }
    }
}