package com.tiangalo.products.application.service;

import com.tiangalo.products.application.port.in.CreateCategoryUseCase;
import com.tiangalo.products.application.port.out.CategoryRepositoryPort;
import com.tiangalo.products.domain.model.Category;
import com.tiangalo.products.domain.model.CategoryId;

public class CreateCategoryService implements CreateCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    public CreateCategoryService(CategoryRepositoryPort categoryRepositoryPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
    }

    @Override
    public CategoryId create(String name) {
        Category category = Category.create(name);
        if(categoryRepositoryPort.existsByName(name)){
            throw new IllegalArgumentException("Category already exists");
        }
        categoryRepositoryPort.save(category);
        return category.getId();
    }
}
