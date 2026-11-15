package com.tiangalo.products.domain.model;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryIdTest {

    @Test
    void shouldCreateCategoryIdWithValidUUID() {
        UUID uuid = UUID.randomUUID();

        CategoryId categoryId = new CategoryId(uuid);

        assertNotNull(categoryId);
        assertEquals(uuid, categoryId.uuid());
    }

    @Test
    void shouldThrowExceptionWhenUUIDIsNull(){
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> new CategoryId(null));
        assertEquals("Category id cannot be null", exception.getMessage());
    }

    @Test
    void shouldBeEqualWhenUUIDIsEqual(){
        UUID uuid = UUID.randomUUID();
        CategoryId first = new CategoryId(uuid);
        CategoryId second = new CategoryId(uuid);

        assertEquals(first, second);
        assertNotSame(first, second);
    }
}
