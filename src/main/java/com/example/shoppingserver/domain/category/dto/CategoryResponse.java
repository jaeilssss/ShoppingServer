package com.example.shoppingserver.domain.category.dto;

import com.example.shoppingserver.domain.category.entity.Category;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CategoryResponse {
    private Long categoryId;
    private String name;
    private Long depth;
    private List<CategoryResponse> children;


    public CategoryResponse createCategoryResponse(Category category) {
        this.categoryId = category.getCategoryId();
        this.name = category.getName();
        this.depth = category.getDepth();
        ArrayList<CategoryResponse> list = new ArrayList<>();

        category.getChildren().forEach(child ->
          list.add(new CategoryResponse().createCategoryResponse(child))
        );

        children = list;
        return this;
    }
}
