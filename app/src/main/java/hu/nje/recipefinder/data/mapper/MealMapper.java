package hu.nje.recipefinder.data.mapper;

import java.util.ArrayList;
import java.util.List;

import hu.nje.recipefinder.data.remote.dtos.MealDto;
import hu.nje.recipefinder.domain.Ingredient;
import hu.nje.recipefinder.domain.Recipe;

public class MealMapper {

    public static List<Recipe> toDomainList(List<MealDto> meals){
        List<Recipe> recipes = new ArrayList<>();

        if(meals == null) return recipes;

        for(MealDto meal : meals){
            Recipe recipe = toDomain(meal);
            recipes.add(recipe);
        }

        return recipes;
    }

    private static Recipe toDomain(MealDto meal) {
        return new Recipe(
                meal.id, meal.name, meal.category, meal.area,
                meal.instructions, meal.imageUrl, mapIngredients(meal)
        );
    }

    private static List<Ingredient> mapIngredients(MealDto meal){
        List<Ingredient> ingredients = new ArrayList<>();

        addIfNotEmpty(ingredients, meal.ingredient1, meal.measure1);
        addIfNotEmpty(ingredients, meal.ingredient2, meal.measure2);
        addIfNotEmpty(ingredients, meal.ingredient3, meal.measure3);
        addIfNotEmpty(ingredients, meal.ingredient4, meal.measure4);
        addIfNotEmpty(ingredients, meal.ingredient5, meal.measure5);
        addIfNotEmpty(ingredients, meal.ingredient6, meal.measure6);
        addIfNotEmpty(ingredients, meal.ingredient7, meal.measure7);
        addIfNotEmpty(ingredients, meal.ingredient8, meal.measure8);
        addIfNotEmpty(ingredients, meal.ingredient9, meal.measure9);
        addIfNotEmpty(ingredients, meal.ingredient10, meal.measure10);
        addIfNotEmpty(ingredients, meal.ingredient11, meal.measure11);
        addIfNotEmpty(ingredients, meal.ingredient12, meal.measure12);
        addIfNotEmpty(ingredients, meal.ingredient13, meal.measure13);
        addIfNotEmpty(ingredients, meal.ingredient14, meal.measure14);
        addIfNotEmpty(ingredients, meal.ingredient15, meal.measure15);
        addIfNotEmpty(ingredients, meal.ingredient16, meal.measure16);
        addIfNotEmpty(ingredients, meal.ingredient17, meal.measure17);
        addIfNotEmpty(ingredients, meal.ingredient18, meal.measure18);
        addIfNotEmpty(ingredients, meal.ingredient19, meal.measure19);
        addIfNotEmpty(ingredients, meal.ingredient20, meal.measure20);

        return ingredients;
    }

    private static void addIfNotEmpty(List<Ingredient> list, String ingredient, String measure){
        if(ingredient != null && !ingredient.trim().isEmpty() ){
            String trimmedName = ingredient.trim();
            String trimmedMeasure = measure != null ? measure.trim() : "";

            list.add(new Ingredient(trimmedName, trimmedMeasure));
        }
    }
}
