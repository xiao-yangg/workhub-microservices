package net.workhub.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainEmployeeEvent {
    private String message;
    private String status;
    private DomainEmployee domainEmployee;
}
