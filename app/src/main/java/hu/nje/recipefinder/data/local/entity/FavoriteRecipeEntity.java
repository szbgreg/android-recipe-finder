package hu.nje.recipefinder.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_recipes")
public class FavoriteRecipeEntity {

    @PrimaryKey
    @NonNull
    public String idMeal;

    public String name;
    public String thumbnail;
    public String category;

    public FavoriteRecipeEntity(@NonNull String idMeal, String name, String thumbnail, String category) {
        this.idMeal = idMeal;
        this.name = name;
        this.thumbnail = thumbnail;
        this.category = category;
    }
}