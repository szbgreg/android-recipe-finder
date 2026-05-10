package hu.nje.recipefinder.data.remote;

import hu.nje.recipefinder.data.remote.dtos.CategoryListResponse;
import hu.nje.recipefinder.data.remote.dtos.MealListResponse;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealApiService {
    public final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private final TheMealDBClient client;

    public MealApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(TheMealDBClient.class);
    }

    public void searchByName(String name, Callback<MealListResponse> callback) {
        client.searchByName(name).enqueue(callback);
    }

    public void getById(String id, Callback<MealListResponse> callback) {
        client.getById(id).enqueue(callback);
    }

    public void getCategories(Callback<CategoryListResponse> callback) {
        client.getCategories().enqueue(callback);
    }

    public void filterByCategory(String category, Callback<MealListResponse> callback) {
        client.filterByCategory(category).enqueue(callback);
    }
}
