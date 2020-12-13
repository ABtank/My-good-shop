package ru.abtank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.abtank.persist.model.Category;
import ru.abtank.persist.repositories.*;
import ru.abtank.representations.CategoryRepr;
import ru.abtank.servises.CategoryService;
import ru.abtank.servises.CategoryServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @BeforeEach
    public void init() {
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void testFindById() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1);
        expectedCategory.setName("Category test name");

        when(categoryRepository.findById(eq(1)))
                .thenReturn(Optional.of(expectedCategory));

        Optional<CategoryRepr> opt = categoryService.findById(1);

        assertTrue(opt.isPresent());
        assertEquals(expectedCategory.getId(), opt.get().getId());
        assertEquals(expectedCategory.getName(), opt.get().getName());

    }
}
