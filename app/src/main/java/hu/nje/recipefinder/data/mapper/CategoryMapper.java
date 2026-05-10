package hu.nje.recipefinder.data.mapper;

import java.util.ArrayList;
import java.util.List;

import hu.nje.recipefinder.data.remote.dtos.CategoryDto;
import hu.nje.recipefinder.domain.Category;

public class CategoryMapper {

    public static List<Category> toDomainList(List<CategoryDto> dtoList){
        List<Category> list = new ArrayList<>();

        if(dtoList == null) return list;

        for(CategoryDto category : dtoList){
            list.add(toDomain(category));
        }

        return list;
    }

    private static Category toDomain(CategoryDto category){
        return new Category(category.id, category.name, category.imageUrl);
    }
}
