package hu.nje.recipefinder.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.nje.recipefinder.data.local.entity.FavoriteRecipeEntity;

@Dao
public interface FavoriteRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoriteRecipeEntity recipe);

    @Delete
    void delete(FavoriteRecipeEntity recipe);

    @Query("DELETE FROM favorite_recipes WHERE idMeal = :idMeal")
    void deleteById(String idMeal);

    @Query("SELECT * FROM favorite_recipes")
    List<FavoriteRecipeEntity> getAllFavorites();

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_recipes WHERE idMeal = :idMeal)")
    boolean isFavorite(String idMeal);
}