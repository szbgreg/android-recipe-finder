package hu.nje.recipefinder.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shopping_list")
public class ShoppingListItemEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String recipeName;
    public String ingredientName;
    public String measure;
    public int servings;

    public ShoppingListItemEntity(String recipeName, String ingredientName, String measure, int servings) {
        this.recipeName = recipeName;
        this.ingredientName = ingredientName;
        this.measure = measure;
        this.servings = servings;
    }
}