package ru.abtank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.abtank.representation.TextMessage;

// получаем сообщения
@RabbitListener(queues = "#{'${user.name}' + '.queue'}")
public class RabbitMqReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @RabbitHandler
    public void receive(TextMessage msg) {
        logger.info("New message '{}' from user '{}'", msg.getText(), msg.getSender());
    }
}