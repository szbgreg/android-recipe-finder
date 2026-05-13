package hu.nje.recipefinder.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "search_history")
public class SearchHistoryEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String searchText;
    public long timestamp;

    public SearchHistoryEntity(String searchText, long timestamp) {
        this.searchText = searchText;
        this.timestamp = timestamp;
    }
}