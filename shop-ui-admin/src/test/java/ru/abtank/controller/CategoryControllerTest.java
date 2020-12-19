package ru.abtank.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.abtank.persist.model.Category;
import ru.abtank.persist.repositories.CategoryRepository;
import ru.abtank.servise.StockService;

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
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private CategoryRepository categoryRepository;
    @MockBean
    private EurekaClient eurekaClient;

    @BeforeEach
    public void init(){
        categoryRepository.deleteAllInBatch(); // очистка БД перед тестом
        InstanceInfo instanceInfo = mock(InstanceInfo.class);
        when(instanceInfo.getHomePageUrl()).thenReturn("mock-homepage-url");

        when(eurekaClient.getNextServerFromEureka(anyString(), anyBoolean()))
                .thenReturn(instanceInfo);
    }

    @MockBean
    private StockService stockService;

    @WithMockUser(value = "admin", password = "123", roles = {"ADMIN"})  // для регистрации в spring security
    @Test
    public void testCategoryCreation() throws Exception {
        //проверка отработки самого запроса
        mvc.perform(post("/categories/save")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "-1")
        .param("name","New Category")
        .with(csrf()) //мы используем csrf для контроля безопасности
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/categories"));

        //проверка добавился ли продукт в БД
        Optional<Category> category = categoryRepository.findOne(Example.of(new Category("New Category")));

        Assertions.assertTrue(category.isPresent());
        Assertions.assertEquals("New Category", category.get().getName());
    }
}
