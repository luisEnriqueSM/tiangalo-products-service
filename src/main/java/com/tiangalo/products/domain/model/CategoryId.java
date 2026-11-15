package com.tiangalo.products.domain.model;

import java.util.Objects;
import java.util.UUID;

public record CategoryId(
        UUID uuid
) {
    public CategoryId {
        Objects.requireNonNull(uuid, "Category id cannot be null");
    }

    public static CategoryId newId(){
        return new CategoryId(UUID.randomUUID());
    }
}
