package hu.nje.recipefinder.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import hu.nje.recipefinder.data.local.entity.ShoppingListItemEntity;

@Dao
public interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ShoppingListItemEntity item);

    @Query("SELECT * FROM shopping_list ORDER BY recipeName ASC, id ASC")
    List<ShoppingListItemEntity> getAllItems();

    @Query("DELETE FROM shopping_list")
    void clearAll();

    @Query("DELETE FROM shopping_list WHERE id = :id")
    void deleteById(int id);
}