package ru.abtank.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.abtank.controllers.ProductsController;
import ru.abtank.persist.model.*;
import ru.abtank.persist.repositories.*;
import ru.abtank.servise.Stock;
import ru.abtank.servise.StockService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yaml")
@SpringBootTest (classes = {ProductControllerTest.TestConfig.class})  // помимо создания класса конфигурации его необходимо объявить
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @MockBean
    private EurekaClient eurekaClient;
//    либо так
//    @MockBean
//    private StockService stockService;
//    либо так
    @TestConfiguration
    public static class TestConfig {

        @Bean
        public StockService stockService() {
            return id -> new Stock(id, 23);
        }
    }

    @BeforeEach
    public void init() {
        // очистка БД перед тестом
        brandRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();
        statusRepository.deleteAllInBatch();
        productTypeRepository.deleteAllInBatch();

        InstanceInfo instanceInfo = mock(InstanceInfo.class);
        when(instanceInfo.getHomePageUrl()).thenReturn("mock-homepage-url");

        when(eurekaClient.getNextServerFromEureka(anyString(), anyBoolean()))
                .thenReturn(instanceInfo);
    }


    @WithMockUser(value = "admin", password = "123", roles = {"ADMIN"})  // для регистрации в spring security
    @Test
    public void testProductCreation() throws Exception {
//        создаем бренд
        mvc.perform(post("/brands/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "-1")
                .param("name", "New brand")
                .with(csrf()) //мы используем csrf для контроля безопасности
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/brands"));

        //проверка добавился ли продукт в БД
        Optional<Brand> brand = brandRepository.findOne(Example.of(new Brand("New brand")));

        Assertions.assertTrue(brand.isPresent());
        Assertions.assertEquals("New brand", brand.get().getName());

//        создаем категорию
        mvc.perform(post("/categories/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "-1")
                .param("name", "New Category")
                .with(csrf()) //мы используем csrf для контроля безопасности
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/categories"));

        //проверка добавился ли продукт в БД
        Optional<Category> category = categoryRepository.findOne(Example.of(new Category("New Category")));

        Assertions.assertTrue(category.isPresent());
        Assertions.assertEquals("New Category", category.get().getName());

//        создаем статус
        mvc.perform(post("/statuses/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "-1")
                .param("name", "New Status")
                .with(csrf()) //мы используем csrf для контроля безопасности
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/statuses"));

        //проверка добавился ли продукт в БД
        Optional<Status> status = statusRepository.findOne(Example.of(new Status("New Status")));

        Assertions.assertTrue(status.isPresent());
        Assertions.assertEquals("New Status", status.get().getName());

//        создаем тип продукта
        mvc.perform(post("/product_types/save")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "-1")
                .param("name", "New ProductType")
                .with(csrf()) //мы используем csrf для контроля безопасности
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product_types"));

        //проверка добавился ли продукт в БД
        Optional<ProductType> productType = productTypeRepository.findOne(Example.of(new ProductType("New ProductType")));

        Assertions.assertTrue(productType.isPresent());
        Assertions.assertEquals("New ProductType", productType.get().getName());


//        создаем продукт TODO понять как
//        List<String> categories = Arrays.asList("1", "cat");
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.addAll("categories", categories);
//        mvc.perform(post("/products/save")
//        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .params(params)
//        .param("id", "-1")
//        .param("name","New Product")
//        .param("quickDescription","quickDescription New Product")
//        .param("description","description New Product")
//        .param("size","23")
//        .param("discount","15")
//        .param("price","150")
//        .param("cat.name",category.get().getName())
//        .param("cat.id", String.valueOf(category.get().getId()))
//        .param("brand.name", brand.get().getName())
//        .param("brand.id", String.valueOf(brand.get().getId()))
//        .param("status.name", status.get().getName())
//        .param("status.id", String.valueOf(status.get().getId()))
//        .param("pro_type.name", productType.get().getName())
//        .param("pro_type.id", String.valueOf(productType.get().getId()))
//        .with(csrf()) //мы используем csrf для контроля безопасности
//        )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/products"));
//
//        //проверка добавился ли продукт в БД
//        Optional<Product> product = productRepository.
//                findOne(Example.of(new Product("New Product","quickDescription New Product"
//                        ,"description New Product",23,15,new BigDecimal(150)
//                        ,brand.get(),category.get(),status.get(),productType.get(),null)));
//
//        Assertions.assertTrue(product.isPresent());
//        Assertions.assertEquals("New Product", product.get().getName());
    }
}
