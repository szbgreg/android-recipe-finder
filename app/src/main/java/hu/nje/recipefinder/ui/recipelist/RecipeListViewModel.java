package hu.nje.recipefinder.ui.recipelist;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hu.nje.recipefinder.data.mapper.MealMapper;
import hu.nje.recipefinder.data.remote.dtos.MealListResponse;
import hu.nje.recipefinder.data.repository.MealRepository;
import hu.nje.recipefinder.domain.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListViewModel extends ViewModel {

    private MealMapper mapper;
    private MealRepository repository;
    private MutableLiveData<List<Recipe>> recipes;

    public RecipeListViewModel() {
        this.mapper = new MealMapper();
        this.repository = new MealRepository();
        this.recipes = new MutableLiveData<>();
    }

    public MutableLiveData<List<Recipe>> getRecipes() {
        return recipes;
    }

    public void searchByName(String query) {
        repository.searchByName(query, createCallback());
    }

    public void filterByCategory(String category) {
        repository.filterByCategory(category, createCallback());
    }

    private Callback<MealListResponse> createCallback() {
        return new Callback<MealListResponse>() {
            @Override
            public void onResponse(Call<MealListResponse> call, Response<MealListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Recipe> result = mapper.toDomainList(response.body().getMeals());
                    recipes.setValue(result);
                }
            }

            @Override
            public void onFailure(Call<MealListResponse> call, Throwable t) {
                Log.e("RecipeListViewModel", t.getMessage());
                recipes.setValue(null);
            }
        };
    }

}
