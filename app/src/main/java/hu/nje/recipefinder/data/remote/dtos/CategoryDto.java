package hu.nje.recipefinder.data.remote.dtos;

import com.google.gson.annotations.SerializedName;

public class CategoryDto {
    @SerializedName("idCategory")
    public String id;

    @SerializedName("strCategory")
    public String name;

    @SerializedName("strCategoryThumb")
    public String imageUrl;
}
