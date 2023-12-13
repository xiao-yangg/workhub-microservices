package net.workhub.employeeservice.kafka;

import net.workhub.basedomains.dto.DomainEmployeeEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeKafkaProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, DomainEmployeeEvent> kafkaTemplate;

    public EmployeeKafkaProducer(NewTopic topic, KafkaTemplate<String, DomainEmployeeEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(DomainEmployeeEvent event) {
        LOGGER.info(String.format("(Employee Service) Event sent via Kafka => %s", event.toString()));

        // Create message
        Message<DomainEmployeeEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
