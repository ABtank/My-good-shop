package ru.abtank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.*;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.dsl.JpaUpdatingOutboundEndpointSpec;
import org.springframework.integration.jpa.support.PersistMode;
import org.springframework.messaging.MessageHandler;
import ru.abtank.persist.model.Brand;
import ru.abtank.persist.model.Category;

import javax.persistence.EntityManagerFactory;
import java.io.File;

@Configuration
public class ImportConfig {

    private final static Logger logger = LoggerFactory.getLogger(ImportConfig.class);

    @Value("${source.directory.path}")
    private String sourceDirectoryPath;
    @Value("${dest.directory.path}")
    private String destDirectoryPath;

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    //    источник сообщений
    @Bean
    public MessageSource<File> sourceDirectory() {
        FileReadingMessageSource messageSource = new FileReadingMessageSource();
        messageSource.setDirectory(new File(sourceDirectoryPath));
        messageSource.setAutoCreateDirectory(true);
        return messageSource;
    }

    //    обработчик сообщений
    @Bean
    public MessageHandler destDirectory() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(destDirectoryPath));
        handler.setExpectReply(false);
        handler.setDeleteSourceFiles(true);
        return handler;
    }

//    хендлер для сохранение информации в БД
    @Bean
    public JpaUpdatingOutboundEndpointSpec jpaPersistHandler() {
        return Jpa.outboundAdapter(this.entityManagerFactory)
                .entityClass(Category.class)
                .persistMode(PersistMode.PERSIST);
    }

    @Bean
    public IntegrationFlow fileMoveFlow() {

//        StandardIntegrationFlow flow = IntegrationFlows.from(sourceDirectory(), conf -> conf.poller(Pollers.fixedDelay(2000)))
//                .filter(msg -> ((File) msg).getName().endsWith(".txt")) // приходит файл
//                .transform(new FileToStringTransformer())  // преобразовали в текст
//                .split(s -> s.delimiters("\n"))  // делим на сроки
//                .<String, String>transform(String::toUpperCase)   // преобразуем формат
//                .handle(destDirectory())   // записываем в новое место
//                .get();

        StandardIntegrationFlow flow = IntegrationFlows.from(sourceDirectory(), conf -> conf.poller(Pollers.fixedDelay(2000)))
                .filter(msg -> ((File) msg).getName().endsWith(".txt"))
                .transform(new FileToStringTransformer())
                .split(s -> s.delimiters("\n"))  // делим на сроки
                .<String, Brand>transform(name ->{
                    logger.info("New brand '{}", name);
                    Brand brand = new Brand();
                    brand.setName(name);
                    return brand;
                })   // преобразуем формат
                .handle(jpaPersistHandler(), ConsumerEndpointSpec::transactional)   // записываем в новое место
                .get();

        return flow;
    }

}
