package net.workhub.payrollservice.kafka;

import net.workhub.basedomains.dto.DomainEmployeeEvent;
import net.workhub.payrollservice.entity.Payroll;
import net.workhub.payrollservice.repository.PayrollRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeConsumer.class);

    private final PayrollRepository payrollRepository;

    public EmployeeConsumer(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    @KafkaListener(topics="${spring.kafka.topic.name}", groupId="${spring.kafka.consumer.group-id}")
    public void consume(DomainEmployeeEvent event) {
        LOGGER.info(String.format("(Payroll Service) Event received => %s", event.toString()));

        // Save employee event into database
        Payroll payroll = new Payroll();
        payroll.setEmployeeId(event.getDomainEmployee().getId());
        payroll.setEmployeeDepartmentCode(event.getDomainEmployee().getDepartmentCode());
        payroll.setEmployeeOrganizationCode(event.getDomainEmployee().getOrganizationCode());

        payrollRepository.save(payroll);
    }
}
