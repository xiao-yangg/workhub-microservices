package net.workhub.basedomains.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainEmployee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentCode;
    private String organizationCode;
}