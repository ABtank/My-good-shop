package ru.abtank.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.abtank.persist.model.*;
import ru.abtank.persist.repositories.*;
import ru.abtank.representations.ProductRepr;
import ru.abtank.servises.ProductService;
import ru.abtank.servises.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;
    private StatusRepository statusRepository;
    private BrandRepository brandRepository;
    private ProductTypeRepository productTypeRepository;
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void init() {
        productRepository = mock(ProductRepository.class);
        statusRepository = mock(StatusRepository.class);
        brandRepository = mock(BrandRepository.class);
        productTypeRepository = mock(ProductTypeRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        productService = new ProductServiceImpl(productRepository, statusRepository, brandRepository, productTypeRepository, categoryRepository);
    }


    @Test
    public void testFindAll() {
        List<Product> expectedProductList = new ArrayList<>();
        Category expectedCategory1 = new Category();
        expectedCategory1.setId(1);
        expectedCategory1.setName("Category name #1");

        Brand expectedBrand1 = new Brand();
        expectedBrand1.setId(1);
        expectedBrand1.setName("Brand name #1");

        Status expectedStatus1 = new Status();
        expectedStatus1.setId(1);
        expectedStatus1.setName("Status name #1");

        ProductType expectedProductType1 = new ProductType();
        expectedProductType1.setId(1);
        expectedProductType1.setName("ProductType name #1");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("Product name #1");
        expectedProduct.setCategory(expectedCategory1);
        expectedProduct.setBrand(expectedBrand1);
        expectedProduct.setStatus(expectedStatus1);
        expectedProduct.setType(expectedProductType1);
        expectedProduct.setPictures(new ArrayList<>());
        expectedProduct.setPrice(new BigDecimal(12345));

//        програмируем поведение мокнутого класса
        when(productRepository.findById(eq(1L)))
                .thenReturn(Optional.of(expectedProduct));

        Optional<ProductRepr> opt = productService.findById(1L);


        for (int i = 1; i <= 10; i++) {
            Category expectedCategory = new Category();
            expectedCategory.setId(i);
            expectedCategory.setName("Category name #" + i);

            Brand expectedBrand = new Brand();
            expectedBrand.setId(i);
            expectedBrand.setName("Brand name #" + i);

            Status expectedStatus = new Status();
            expectedStatus.setId(i);
            expectedStatus.setName("Status name #" + i);

            ProductType expectedProductType = new ProductType();
            expectedProductType.setId(i);
            expectedProductType.setName("ProductType name #" + i);

            Product expectedProductRundoms = new Product();
            expectedProductRundoms.setId((long) i);
            expectedProductRundoms.setName("Product name #" + i);
            expectedProductRundoms.setCategory(expectedCategory);
            expectedProductRundoms.setBrand(expectedBrand);
            expectedProductRundoms.setStatus(expectedStatus);
            expectedProductRundoms.setType(expectedProductType);
            expectedProductRundoms.setPictures(new ArrayList<>());
            expectedProductRundoms.setPrice(new BigDecimal(i * 12345));
            expectedProductList.add(expectedProductRundoms);
        }

        when(productRepository.findAll())
                .thenReturn(expectedProductList);

        List<ProductRepr> all = productService.findAll();

        expectedProductList.stream().map(ProductRepr::new).collect(Collectors.toList());

        Assertions.assertTrue(opt.isPresent());
        Assertions.assertFalse(expectedProductList.isEmpty());
        Assertions.assertEquals(all.size(), expectedProductList.size());
        Assertions.assertEquals(all.get(0).getId(), opt.get().getId());
        Assertions.assertEquals(all.get(0).getName(), opt.get().getName());
        Assertions.assertEquals(all.get(0).getCategory(), opt.get().getCategory());
        Assertions.assertEquals(all.get(0).getBrand(), opt.get().getBrand());
        Assertions.assertEquals(all.get(0).getStatus(), opt.get().getStatus());
        Assertions.assertEquals(all.get(0).getType(), opt.get().getType());
    }
}
