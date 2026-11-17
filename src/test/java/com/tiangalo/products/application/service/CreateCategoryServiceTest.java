package com.tiangalo.products.application.service;

import com.tiangalo.products.application.port.out.CategoryRepositoryPort;
import com.tiangalo.products.domain.model.Category;
import com.tiangalo.products.domain.model.CategoryId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CreateCategoryServiceTest {

    private CategoryRepositoryPort categoryRepositoryPort;
    private CreateCategoryService createCategoryService;

    @BeforeEach
    void setUp() {
        this.categoryRepositoryPort = Mockito.mock(CategoryRepositoryPort.class);
        this.createCategoryService = new CreateCategoryService(categoryRepositoryPort);
    }

    @Test
    void createShouldSaveValidCategory(){
        String name = "Smart TV";
        when(categoryRepositoryPort.existsByName(name)).thenReturn(false);
        CategoryId id = createCategoryService.create(name);
        assertThat(id).isNotNull();

        verify(categoryRepositoryPort, times(1)).existsByName(name);

        ArgumentCaptor<Category> captor =  ArgumentCaptor.forClass(Category.class);
        verify(categoryRepositoryPort).save(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo(name);
        assertThat(captor.getValue().getId()).isEqualTo(id);
    }

    @Test
    void createShouldRejectDuplicateCategory(){
        String name = "Smart TV";
        when(categoryRepositoryPort.existsByName(name)).thenReturn(true);
        assertThatThrownBy(() -> createCategoryService.create(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Category already exists");
        verify(categoryRepositoryPort, times(1)).existsByName(name);
        verify(categoryRepositoryPort, never()).save(any(Category.class));
    }

    @Test
    void createShouldRejectInvalidName(){
        String name = "  ";
        assertThatThrownBy(() -> createCategoryService.create(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is required");
        assertThatThrownBy(() -> createCategoryService.create(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is required");
        verifyNoInteractions(categoryRepositoryPort);
    }
}