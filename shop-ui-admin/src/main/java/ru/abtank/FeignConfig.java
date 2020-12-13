package ru.abtank;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@EnableFeignClients  //для работы с shop-service
@Configuration
public class FeignConfig {
}
