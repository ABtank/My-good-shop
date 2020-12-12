package ru.abtank;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// program arguments --server.port=8181 --user.name=first.user
@Configuration
public class RabbitMqConfig {

//  заинжектим имя пользователю
    @Value("${user.name}")
    private String userName;

//  создаем отдельную очередь для каждого пользователя
    @Bean
    Queue userQueue() {
        return new Queue(userName + ".queue", false);
    }

//  создаем exchange. Если его нет, то создаем иначе используем тот что есть
    @Bean
    DirectExchange exchange() {
        return new DirectExchange("users.exchange");
    }

//    связываем exchange с очередью
    @Bean
    Binding firstBinding(Queue firstUserQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(firstUserQueue)
                .to(exchange)
                .with(userName); // в качестве routing key используем имя пользователя
    }

//    для отправки сообщения в данный exchange
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

//    для конвертации сообщения в удобный формат (преобразуем msg в JSON)
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

//    листнер для получения сообщений
    @Bean
    public RabbitMqReceiver rabbitMqReceiver() {
        return new RabbitMqReceiver();
    }
}