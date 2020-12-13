package ru.abtank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.abtank.persist.model.ProductType;
import ru.abtank.persist.repositories.ProductTypeRepository;
import ru.abtank.representations.ProductTypeRepr;
import ru.abtank.servises.ProductTypeService;
import ru.abtank.servises.ProductTypeServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductTypeServiceTest {

    private ProductTypeRepository productTypeRepository;
    private ProductTypeService productTypeService;

    @BeforeEach
    public void init() {
        productTypeRepository = mock(ProductTypeRepository.class);
        productTypeService = new ProductTypeServiceImpl(productTypeRepository);
    }

    @Test
    public void testFindById() {
        ProductType expectedProductType = new ProductType();
        expectedProductType.setId(1);
        expectedProductType.setName("ProductType test name");

        when(productTypeRepository.findById(eq(1)))
                .thenReturn(Optional.of(expectedProductType));

        Optional<ProductTypeRepr> opt = productTypeService.findById(1);

        assertTrue(opt.isPresent());
        assertEquals(expectedProductType.getId(), opt.get().getId());
        assertEquals(expectedProductType.getName(), opt.get().getName());

    }
}
