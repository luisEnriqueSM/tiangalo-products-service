package com.tiangalo.products.application.port.in;

import com.tiangalo.products.domain.model.CategoryId;

public interface CreateCategoryUseCase {
    CategoryId create(String name);
}
