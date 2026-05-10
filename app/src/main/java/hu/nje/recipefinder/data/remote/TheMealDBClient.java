package hu.nje.recipefinder.data.remote;

import hu.nje.recipefinder.data.remote.dtos.CategoryListResponse;
import hu.nje.recipefinder.data.remote.dtos.MealListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMealDBClient {

    @GET("search.php")
    Call<MealListResponse> searchByName(@Query("s") String name);

    @GET("lookup.php")
    Call<MealListResponse> getById(@Query("i") String id);

    @GET("filter.php")
    Call<MealListResponse> filterByCategory(@Query("c") String category);

    @GET("categories.php")
    Call<CategoryListResponse> getCategories();
}
