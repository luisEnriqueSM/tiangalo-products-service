package com.tiangalo.products.domain.model;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductIdTest {

    @Test
    void shouldCreateProductIdWithValidUUID(){
        // Arrange
        UUID uuid = UUID.randomUUID();

        // Act
        ProductId productId = new ProductId(uuid);

        // Assert
        assertNotNull(productId);
        assertEquals(uuid, productId.id());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull(){
        // Act & assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ProductId(null)
        );

        assertEquals("Product id cannot be null", exception.getMessage());
    }

    @Test
    void shouldBeEqualWhenUUIDIsTheSame(){
        // Arrange
        UUID uuid = UUID.randomUUID();

        // Act
        ProductId first = new ProductId(uuid);
        ProductId second = new ProductId(uuid);

        // Assert
        assertEquals(first, second);
        assertNotSame(first, second);
    }
}
