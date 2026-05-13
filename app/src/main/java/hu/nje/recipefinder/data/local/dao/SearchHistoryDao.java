package hu.nje.recipefinder.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.nje.recipefinder.data.local.entity.SearchHistoryEntity;

@Dao
public interface SearchHistoryDao {

    @Insert
    void insert(SearchHistoryEntity item);

    @Query("SELECT * FROM search_history ORDER BY timestamp DESC LIMIT 10")
    List<SearchHistoryEntity> getLast10Searches();

    @Query("DELETE FROM search_history WHERE id NOT IN (SELECT id FROM search_history ORDER BY timestamp DESC LIMIT 10)")
    void deleteOldSearches();
}