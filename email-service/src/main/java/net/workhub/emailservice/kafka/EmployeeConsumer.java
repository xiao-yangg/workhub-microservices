package net.workhub.emailservice.kafka;

import net.workhub.basedomains.dto.DomainEmployeeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeConsumer.class);

    @KafkaListener(topics="${spring.kafka.topic.name}", groupId="${spring.kafka.consumer.group-id}")
    public void consume(DomainEmployeeEvent event) {
        LOGGER.info(String.format("(Email Service) Event received => %s", event.toString()));

        // Send email to employee
    }
}
