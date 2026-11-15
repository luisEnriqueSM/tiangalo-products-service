package com.tiangalo.products.domain.model;

import java.util.UUID;

public record ProductId(
        UUID id
) {
    public ProductId {
        if (id == null) {
            throw new IllegalArgumentException("ProductId cannot be null");
        }
    }
}
