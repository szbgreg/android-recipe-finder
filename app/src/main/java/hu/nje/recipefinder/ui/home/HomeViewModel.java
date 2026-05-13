package hu.nje.recipefinder.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hu.nje.recipefinder.data.mapper.CategoryMapper;
import hu.nje.recipefinder.data.remote.dtos.CategoryListResponse;
import hu.nje.recipefinder.data.repository.MealRepository;
import hu.nje.recipefinder.domain.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private CategoryMapper mapper;
    private MealRepository mealRepository;
    private MutableLiveData<List<Category>> categories;

    public HomeViewModel() {
        mapper = new CategoryMapper();
        mealRepository = new MealRepository();
        categories = new MutableLiveData<>();
    }

    public MutableLiveData<List<Category>> getCategories() {
        return categories;
    }

    public void loadCategories() {
        mealRepository.getCategories(new Callback<CategoryListResponse>() {
            @Override
            public void onResponse(Call<CategoryListResponse> call, Response<CategoryListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> result = mapper.toDomainList(response.body().getCategories());
                    categories.setValue(result);
                }
            }

            @Override
            public void onFailure(Call<CategoryListResponse> call, Throwable t) {
                Log.e("HomeViewModel", t.getMessage());
                categories.setValue(null);
            }
        });
    }
}
