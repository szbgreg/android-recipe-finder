package hu.nje.recipefinder.data.remote.dtos;

import java.util.List;

public class MealListResponse {
    private List<MealDto> meals;

    public List<MealDto> getMeals() {
        return meals;
    }
}
