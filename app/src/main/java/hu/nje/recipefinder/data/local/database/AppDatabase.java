package hu.nje.recipefinder.data.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import hu.nje.recipefinder.data.local.dao.FavoriteRecipeDao;
import hu.nje.recipefinder.data.local.entity.FavoriteRecipeEntity;
import hu.nje.recipefinder.data.local.dao.ShoppingListDao;
import hu.nje.recipefinder.data.local.entity.ShoppingListItemEntity;
import hu.nje.recipefinder.data.local.dao.SearchHistoryDao;
import hu.nje.recipefinder.data.local.entity.SearchHistoryEntity;

@Database(
        entities = {
                FavoriteRecipeEntity.class,
                ShoppingListItemEntity.class,
                SearchHistoryEntity.class
        },
        version = 4
)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract FavoriteRecipeDao favoriteRecipeDao();
    public abstract ShoppingListDao shoppingListDao();
    public abstract SearchHistoryDao searchHistoryDao();


    public static synchronized AppDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "recipe_database"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}