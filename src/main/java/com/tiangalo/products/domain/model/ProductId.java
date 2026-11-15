package com.tiangalo.products.domain.model;

import java.util.Objects;
import java.util.UUID;

public record ProductId(
        UUID id
) {
    public ProductId {
        Objects.requireNonNull(id, "ProductId cannot be null");
    }

    public static ProductId newId(){
        return new ProductId(UUID.randomUUID());
    }
}
