package com.tiangalo.products.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class CategoryTest {

    @Test
    void createShouldReturnValidCategory(){
        String name = "Smart TVs";

        Category category = Category.create(name);

        assertThat(category).isNotNull();
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getId()).isNotNull();
    }

    @Test
    void createShouldRejectNullName(){
        assertThatThrownBy(() -> Category.create(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is required");
    }

    @Test
    void createShouldRejectBlankName(){
        assertThatThrownBy(() -> Category.create(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is required");
    }

    @Test
    void createShouldRejectWhitespaceName(){
        assertThatThrownBy(() -> Category.create("    "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is required");
    }

    @Test
    void createShouldGenerateUniqueIds(){
        Category first = Category.create("Smart TVs");
        Category second = Category.create("Smart TVs");

        assertThat(first).isNotNull();
        assertThat(first.getName()).isEqualTo("Smart TVs");

        assertThat(second).isNotNull();
        assertThat(second.getName()).isEqualTo("Smart TVs");

        assertNotSame(first, second);
        assertThat(first.getId()).isNotEqualTo(second.getId());
    }
}
