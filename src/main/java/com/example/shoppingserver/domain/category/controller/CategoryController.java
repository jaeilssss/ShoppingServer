package com.example.shoppingserver.domain.category.controller;

import com.example.shoppingserver.domain.category.dto.CategoryResponse;
import com.example.shoppingserver.domain.category.dto.CategoryRequest;
import com.example.shoppingserver.domain.category.service.CategoryService;
import com.example.shoppingserver.globals.Response;
import com.example.shoppingserver.globals.http.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Response<List<CategoryResponse>> getAllCategory() {
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                categoryService.getAllCategory()
        );
    }
    @GetMapping("/{categoryId}")
    public Response<CategoryResponse> getCategory(@PathVariable("categoryId") Long categoryId) {
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                categoryService.getCategoryInfo(categoryId)
        );
    }

    @PostMapping("/add")
    public Response<Void> addCategory(@RequestBody CategoryRequest request) {
        categoryService.addCategory(request);
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                null
        );
    }

    @PutMapping("/modify/{categoryId}")
    public Response<Void> modifyCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest request) {
        categoryService.modifyCategory(categoryId, request);
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                null
        );
    }

    @DeleteMapping("/delete/{categoryId}")
    public Response<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                null
        );
    }

}
