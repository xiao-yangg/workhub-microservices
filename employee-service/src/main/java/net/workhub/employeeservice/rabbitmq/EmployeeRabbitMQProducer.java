package net.workhub.employeeservice.rabbitmq;

import net.workhub.basedomains.dto.DomainEmployeeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeRabbitMQProducer {

    private Logger LOGGER = LoggerFactory.getLogger(EmployeeRabbitMQProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.binding.routing.key}")
    private String employeeRoutingKey;

    @Value("${rabbitmq.binding.sms.routing.key}")
    private String smsRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public EmployeeRabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(DomainEmployeeEvent event) {
        LOGGER.info(String.format("(Employee Service) Event sent via RabbitMQ => %s", event.toString()));

        // Exchange send event to 2 queues
        rabbitTemplate.convertAndSend(exchange, employeeRoutingKey, event);
        rabbitTemplate.convertAndSend(exchange, smsRoutingKey, event);
    }
}
