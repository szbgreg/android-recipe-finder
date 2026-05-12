package hu.nje.recipefinder.data.repository;

import hu.nje.recipefinder.data.remote.MealApiService;
import hu.nje.recipefinder.data.remote.dtos.CategoryListResponse;
import hu.nje.recipefinder.data.remote.dtos.MealListResponse;
import retrofit2.Callback;

public class MealRepository {

    private MealApiService apiService;

    public MealRepository() {
        this.apiService = new MealApiService();
    }

    public void searchByName(String name, Callback<MealListResponse> callback) {
        apiService.searchByName(name, callback);
    }

    public void getById(String id, Callback<MealListResponse> callback) {
        apiService.getById(id, callback);
    }

    public void getCategories(Callback<CategoryListResponse> callback) {
        apiService.getCategories(callback);
    }

    public void filterByCategory(String category, Callback<MealListResponse> callback) {
        apiService.filterByCategory(category, callback);
    }
}
