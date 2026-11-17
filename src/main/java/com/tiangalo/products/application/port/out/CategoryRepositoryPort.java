package com.tiangalo.products.application.port.out;

import com.tiangalo.products.domain.model.Category;

public interface CategoryRepositoryPort {
    boolean existsByName(String name);
    void save(Category category);
}
