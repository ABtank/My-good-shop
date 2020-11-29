package ru.abtank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  //для работы с shop-service
public class ShopUiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopUiAdminApplication.class, args);
    }

}
