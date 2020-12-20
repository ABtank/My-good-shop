package ru.abtank.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.abtank.persist.model.*;
import ru.abtank.persist.repositories.*;
import ru.abtank.representations.ProductRepr;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yaml")
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @MockBean
    private EurekaClient eurekaClient;

    @BeforeEach
    public void init() {
        InstanceInfo instanceInfo = mock(InstanceInfo.class);
        when(instanceInfo.getHomePageUrl()).thenReturn("mock-homepage-url");

        when(eurekaClient.getNextServerFromEureka(anyString(), anyBoolean()))
                .thenReturn(instanceInfo);
    }

    @WithMockUser(value = "admin", password = "123", roles = {"ADMIN"})  // для регистрации в spring security
    @Test
    public void testProductDetails() throws Exception {
        Brand brand = brandRepository.save(new Brand());
        brand.setName("brand");
        Category category = categoryRepository.save(new Category());
        category.setName("Category");
        Status status = statusRepository.save(new Status());
        status.setName("Status");
        ProductType productType = productTypeRepository.save(new ProductType());
        productType.setName("ProductType");
        Product product = productRepository.save(new Product("product","quickDescription","description",12,10,new BigDecimal(1234), brand, category,status,productType,new ArrayList<Picture>()));

        mvc.perform(get("/products/" + product.getId()))
                .andExpect(status().is2xxSuccessful())  //проверка статусов
                .andExpect(view().name("product-details"))  // проверка представления
                .andExpect(model().attributeExists("product")) // проверка наличия атрибута
                .andExpect(model().attribute("product", new BaseMatcher<Product>() {

                    @Override
                    public void describeTo(Description description) {
                        //костомизация сообщений в лог
                    }

                    @Override
                    public boolean matches(Object o) {
                        if (o instanceof ProductRepr) {
                            ProductRepr productRepr = (ProductRepr) o;
                            return productRepr.getId().equals(product.getId());
                        }
                        return false;
                    }
                }));
    }
}

