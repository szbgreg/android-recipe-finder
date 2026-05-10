package hu.nje.recipefinder.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.data.remote.MealApiService;
import hu.nje.recipefinder.data.remote.dtos.CategoryDto;
import hu.nje.recipefinder.data.remote.dtos.CategoryListResponse;
import hu.nje.recipefinder.data.remote.dtos.MealDto;
import hu.nje.recipefinder.data.remote.dtos.MealListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    MealApiService apiService = new MealApiService();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        apiService.searchByName("chicken", new Callback<MealListResponse>() {
            @Override
            public void onResponse(Call<MealListResponse> call, Response<MealListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MealDto> meals = response.body().getMeals();

                    Log.d("TEST Meal size", "Results: " + meals.size());
                    Log.d("TEST Meal name", "First meal: " + meals.get(0).name);
                }
            }

            @Override
            public void onFailure(Call<MealListResponse> call, Throwable e) {
                Log.d("TEST", "Fail: " + e.getMessage());
            }
        });

        return view;
    }
}