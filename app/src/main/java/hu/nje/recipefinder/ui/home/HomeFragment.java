package hu.nje.recipefinder.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.nje.recipefinder.R;
import hu.nje.recipefinder.data.mapper.CategoryMapper;
import hu.nje.recipefinder.data.mapper.MealMapper;
import hu.nje.recipefinder.data.remote.MealApiService;
import hu.nje.recipefinder.data.remote.dtos.CategoryDto;
import hu.nje.recipefinder.data.remote.dtos.CategoryListResponse;
import hu.nje.recipefinder.data.remote.dtos.MealDto;
import hu.nje.recipefinder.data.remote.dtos.MealListResponse;
import hu.nje.recipefinder.domain.Category;
import hu.nje.recipefinder.domain.Recipe;
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

        apiService.getCategories(new Callback<CategoryListResponse>() {
            @Override
            public void onResponse(Call<CategoryListResponse> call, Response<CategoryListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CategoryDto> dtoList = response.body().getCategories();
                    List<Category> categories = CategoryMapper.toDomainList(dtoList);

                    RecyclerView recyclerView = view.findViewById(R.id.categoriesRecyclerView);

                    CategoryListAdapter adapter = new CategoryListAdapter(categories);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<CategoryListResponse> call, Throwable e) {
                Log.d("API_GET_CATEGORIES", "Fail: " + e.getMessage());
            }
        });

        return view;
    }
}