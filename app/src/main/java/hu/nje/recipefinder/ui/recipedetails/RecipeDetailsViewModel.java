package hu.nje.recipefinder.ui.recipedetails;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hu.nje.recipefinder.data.mapper.MealMapper;
import hu.nje.recipefinder.data.remote.dtos.MealDto;
import hu.nje.recipefinder.data.remote.dtos.MealListResponse;
import hu.nje.recipefinder.data.repository.MealRepository;
import hu.nje.recipefinder.domain.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetailsViewModel extends ViewModel {
    private MealRepository repository;
    private MutableLiveData<Recipe> recipe = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public RecipeDetailsViewModel() {
        repository = new MealRepository();
    }

    public LiveData<Recipe> getRecipe() {
        return recipe;
    }

    public void loadRecipe(String mealId) {
        isLoading.setValue(true);
        repository.getById(mealId, new Callback<MealListResponse>() {
            @Override
            public void onResponse(Call<MealListResponse> call, Response<MealListResponse> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    List<MealDto> meals = response.body().getMeals();
                    if (meals != null) {
                        recipe.setValue(MealMapper.toDomainList(meals).get(0));
                    }
                }
            }

            @Override
            public void onFailure(Call<MealListResponse> call, Throwable t) {
                isLoading.setValue(false);
                Log.e("RecipeDetailsViewModel", t.getMessage());
                recipe.setValue(null);
            }
        });
    }
}
