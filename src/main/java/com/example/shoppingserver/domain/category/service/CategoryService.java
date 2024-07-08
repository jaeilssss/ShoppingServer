package com.example.shoppingserver.domain.category.service;

import com.example.shoppingserver.domain.category.enums.CategoryErrorCode;
import com.example.shoppingserver.domain.category.dao.CategoryRepository;
import com.example.shoppingserver.domain.category.dto.CategoryResponse;
import com.example.shoppingserver.domain.category.dto.CategoryRequest;
import com.example.shoppingserver.domain.category.entity.Category;
import com.example.shoppingserver.globals.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponse getCategoryInfo(Long categoryId) {
        return new CategoryResponse()
                .createCategoryResponse(getCategory(categoryId));
    }

    @Transactional
    public void addCategory(CategoryRequest request) {
        Category parent = null;
        if(request.getParentId() != -1) {
            parent = getCategory(request.getParentId());
        }
        categoryRepository.save(Category.createCategoryByNewCategory(request, parent));
    }

    public Category getCategory(Long categoryId) {
       return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new MyException(
                        CategoryErrorCode.NOT_EXIST_CATEGORY.getCode(),
                        CategoryErrorCode.NOT_EXIST_CATEGORY.getMessage()));
    }

    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.findAllByParentNull()
                .stream()
                .map(it -> new CategoryResponse().createCategoryResponse(it))
                .collect(Collectors.toList());
    }

    @Transactional
    public void modifyCategory(Long categoryId, CategoryRequest request) {
        Category category = getCategory(categoryId);

        category.setName(request.getCategoryName());
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        // 테스트 해봐야할듯!! 그냥 지웠을 경우 child는 어떻게 되는지 궁금!!
        categoryRepository.delete(getCategory(categoryId));
    }
}
