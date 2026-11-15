package com.tiangalo.products.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotSame;


public class ProductTest {

    @Test
    void createShouldReturnDraftProduct(){
        String name = "Smart TV";
        String description = "55 inches TV";
        CategoryId categoryId = CategoryId.newId();
        Product product = Product.create(name, description, categoryId);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getDescription()).isEqualTo(description);
        assertThat(product.getCategoryId()).isEqualTo(categoryId);
        assertThat(product.getStatus()).isEqualTo(ProductStatus.DRAFT);
    }

    @Test
    void createShouldRejectBlankName(){
        String name = "";
        String description = "55 inches TV";
        CategoryId categoryId = CategoryId.newId();
        assertThatThrownBy(() -> Product.create(name, description, categoryId))
                .hasMessage("name is required");
    }

    @Test
    void createShouldRejectNullName(){
        String description = "55 inches TV";
        CategoryId categoryId = CategoryId.newId();
        assertThatThrownBy(() -> Product.create(null, description, categoryId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is required");
    }

    @Test
    void createShouldRejectSpaceName(){
        String name = "     ";
        String description = "55 inches TV";
        CategoryId categoryId = CategoryId.newId();
        assertThatThrownBy(() -> Product.create(name, description, categoryId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is required");
    }

    @Test
    void createShouldRejectBlankDescription(){
        String name = "Smart TV";
        String description = "";
        CategoryId categoryId = CategoryId.newId();
        assertThatThrownBy(() -> Product.create(name, description, categoryId))
                .hasMessage("description is required");
    }

    @Test
    void createShouldRejectNullDescription(){
        String name = "Smart TV";
        CategoryId categoryId = CategoryId.newId();
        assertThatThrownBy(() -> Product.create(name, null, categoryId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("description is required");
    }

    @Test
    void createShouldRejectSpaceDescription(){
        String name = "Smart TV";
        String description = "    ";
        CategoryId categoryId = CategoryId.newId();
        assertThatThrownBy(() -> Product.create(name, description, categoryId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("description is required");
    }

    @Test
    void createShouldRejectNullCategoryId(){
        String name = "Smart TV";
        String description = "55 inches TV";

        assertThatThrownBy(() -> Product.create(name, description, null))
                .hasMessage("CategoryId is required");
    }

    @Test
    void createShouldCreateDifferentInstance(){
        String name = "Smart TV";
        String description = "55 inches TV";
        CategoryId categoryId = CategoryId.newId();

        Product first = Product.create(name, description, categoryId);
        Product second = Product.create(name, description, categoryId);

        assertThat(first).isNotNull();
        assertThat(second).isNotNull();
        assertNotSame(first, second);
        assertThat(first.getId()).isNotEqualTo(second.getId());
    }

    @Test
    void renameShouldUpdateName(){
        String name = "Smart TV";
        String description = "55 inches TV";
        CategoryId categoryId = CategoryId.newId();
        Product product = Product.create(name, description, categoryId);
        ProductId productId = product.getId();

        String newName = "Samsung Smart TV";
        product.rename(newName);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(productId);
        assertThat(product.getName()).isEqualTo(newName);
        assertThat(product.getDescription()).isEqualTo(description);
        assertThat(product.getCategoryId()).isEqualTo(categoryId);
        assertThat(product.getStatus()).isEqualTo(ProductStatus.DRAFT);
    }

    @Test
    void renameShouldRejectNullName(){
        String name = "Smart TV";
        String description = "55 inches TV";
        CategoryId categoryId = CategoryId.newId();
        Product product = Product.create(name, description, categoryId);

        assertThatThrownBy(() -> product.rename(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is required");
    }

    @Test
    void renameShouldRejectBlankName(){
        String name = "Smart TV";
        String description = "55 inches TV";
        CategoryId categoryId = CategoryId.newId();
        Product product = Product.create(name, description, categoryId);

        assertThatThrownBy(() -> product.rename(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is required");
    }

    @Test
    void renameShouldRejectWhitespaceName(){
        String name = "Smart TV";
        String description = "55 inches TV";
        CategoryId categoryId = CategoryId.newId();
        Product product = Product.create(name, description, categoryId);

        assertThatThrownBy(() -> product.rename(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is required");
    }

    @Test
    void renameShouldPreserveNameOnFailure(){
        String name = "Smart TV";
        String description = "55 inches TV";
        CategoryId categoryId = CategoryId.newId();
        Product product = Product.create(name, description, categoryId);

        assertThatThrownBy(() -> product.rename(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is required");

        assertThat(product.getName()).isEqualTo(name);
    }

    @Test
    void renameShouldIgnoreSameName(){
        String name = "Smart TV";
        String description = "55 inches TV";
        CategoryId categoryId = CategoryId.newId();
        Product product = Product.create(name, description, categoryId);

        product.rename(name);
        assertThat(product.getName()).isEqualTo(name);
    }
}
