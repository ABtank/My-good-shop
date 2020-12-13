package ru.abtank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FeignConfig.class) // для правильной отработки тестов вынесли класс и указали ему профиль
public class ShopUiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopUiAdminApplication.class, args);
    }

}
