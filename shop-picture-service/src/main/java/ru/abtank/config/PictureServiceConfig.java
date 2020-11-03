package ru.abtank.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.abtank.persist.repositories.PictureRepository;
import ru.abtank.service.PictureService;
import ru.abtank.service.PictureServiceBlobImpl;
import ru.abtank.service.PictureServiceFileImpl;

@Configuration
public class PictureServiceConfig {

    @Bean
    @ConditionalOnProperty(name = "picture.storage.type", havingValue = "database")
    public PictureService pictureServiceBlobImpl(PictureRepository pictureRepository) {
        return new PictureServiceBlobImpl(pictureRepository);
    }

    @Bean
    @ConditionalOnProperty(name = "picture.storage.type", havingValue = "files")
    public PictureService pictureServiceFileImpl(PictureRepository pictureRepository) {
        return new PictureServiceFileImpl(pictureRepository);
    }
}
