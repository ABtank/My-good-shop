package ru.abtank.controller;

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
import ru.abtank.persist.model.Brand;
import ru.abtank.persist.repositories.BrandRepository;
import ru.abtank.servise.StockService;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yaml")
@SpringBootTest
@AutoConfigureMockMvc
public class BrandControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private BrandRepository brandRepository;

    @BeforeEach
    public void init(){
        brandRepository.deleteAllInBatch(); // очистка БД перед тестом
    }

    @MockBean
    private StockService stockService;

    @WithMockUser(value = "admin", password = "123", roles = {"ADMIN"})  // для регистрации в spring security
    @Test
    public void testBrandCreation() throws Exception {
        //проверка отработки самого запроса
        mvc.perform(post("/brands/save")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "-1")
        .param("name","New brand")
        .with(csrf()) //мы используем csrf для контроля безопасности
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/brands"));

        //проверка добавился ли продукт в БД
        Optional<Brand> brand = brandRepository.findOne(Example.of(new Brand("New brand")));

        Assertions.assertTrue(brand.isPresent());
        Assertions.assertEquals("New brand", brand.get().getName());
    }
}
