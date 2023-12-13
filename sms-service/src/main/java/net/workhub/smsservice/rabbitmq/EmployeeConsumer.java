package net.workhub.smsservice.rabbitmq;

import net.workhub.basedomains.dto.DomainEmployeeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    private Logger LOGGER = LoggerFactory.getLogger(EmployeeConsumer.class);

    @RabbitListener(queues="${rabbitmq.queue.sms.name}")
    public void consume(DomainEmployeeEvent event) {
        LOGGER.info(String.format("(Email Service) Event received via RabbitMQ => %s", event.toString()));

        // Send SMS to employee
    }
}
