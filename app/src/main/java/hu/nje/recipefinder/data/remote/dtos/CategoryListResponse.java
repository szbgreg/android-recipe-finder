package hu.nje.recipefinder.data.remote.dtos;

import java.util.List;

public class CategoryListResponse {
    private List<CategoryDto> categories;

    public List<CategoryDto> getCategories() {
        return categories;
    }
}
