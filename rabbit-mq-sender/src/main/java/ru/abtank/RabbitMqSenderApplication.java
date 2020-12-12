package ru.abtank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Для работы необходимо установить RabbitMQ
 * https://www.rabbitmq.com
 * Установка https://erlang.org/download/otp_versions_tree.html
 * Установка https://github.com/rabbitmq/rabbitmq-server/releases
 *  rabbitmq-plugins enable rabbitmq_management
 *  rabbitmq-plugins enable rabbitmq_stomp
 *  rabbitmq-server
 *  http://localhost:15672/
 *  login: guest
 *  password: guest
 */


@SpringBootApplication
public class RabbitMqSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqSenderApplication.class, args);
	}

}
