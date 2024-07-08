package com.example.shoppingserver.domain.category.entity;

import com.example.shoppingserver.domain.category.dto.CategoryRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Category parent;

    private Long depth;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    public static Category createCategoryByNewCategory(CategoryRequest categoryRequest, Category parent) {
        long depth = 1;
        if(parent != null) depth++;
        return Category.builder()
                .name(categoryRequest.getCategoryName())
                .parent(parent)
                .depth(depth)
                .build();
    }
}
