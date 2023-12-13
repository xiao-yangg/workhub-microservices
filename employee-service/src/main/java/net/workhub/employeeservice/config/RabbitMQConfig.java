package net.workhub.employeeservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.employee.name}")
    private String employeeQueue;

    @Value("${rabbitmq.queue.sms.name}")
    private String smsQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.binding.routing.key}")
    private String employeeRoutingKey;

    @Value("${rabbitmq.binding.sms.routing.key}")
    private String smsRoutingKey;

    // Queue - Employee
    @Bean
    public Queue employeeQueue() {
        return new Queue(employeeQueue);
    }

    // Queue - SMS
    @Bean
    public Queue smsQueue() {
        return new Queue(smsQueue);
    }

    // Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // Bind exchange and queue using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(employeeQueue())
                .to(exchange())
                .with(employeeRoutingKey);
    }
    @Bean
    public Binding smsBinding() {
        return BindingBuilder.bind(smsQueue())
                .to(exchange())
                .with(smsRoutingKey);
    }

    // Message converter
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    // Configure RabbitTemplate
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
