package hu.nje.recipefinder.ui.recipelist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.ui.home.CategoryListAdapter;

public class RecipeListFragment extends Fragment {

    private String type;
    private String query;
    private RecipeListViewModel viewModel;
    private RecipeListAdapter adapter;
    private TextView emptyTextView;
    private RecyclerView recyclerView;
    NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);

        if (getArguments() != null) {
            type = getArguments().getString("type");
            query = getArguments().getString("query");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        emptyTextView = view.findViewById(R.id.emptyTextView);
        recyclerView = view.findViewById(R.id.recipesRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        initRecyclerView(view);

        viewModel.getRecipes().observe(getViewLifecycleOwner(), recipes -> {
            adapter.setRecipes(recipes);
            adapter.notifyDataSetChanged();
            emptyTextView.setVisibility(recipes.isEmpty() ? View.VISIBLE : View.GONE);
            recyclerView.setVisibility(recipes.isEmpty() ? View.GONE : View.VISIBLE);
        });

        if (type.equals("category")) {
            viewModel.filterByCategory(query);
        } else {
            viewModel.searchByName(query);
        }
    }

    private void initRecyclerView(View view) {

        adapter = new RecipeListAdapter(navController);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}