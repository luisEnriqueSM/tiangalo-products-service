package com.tiangalo.products.domain.model;

import java.util.UUID;

public record CategoryId(
        UUID uuid
) {
    public CategoryId {
        if (uuid == null) {
            throw new IllegalArgumentException("Category id cannot be null");
        }
    }
}
