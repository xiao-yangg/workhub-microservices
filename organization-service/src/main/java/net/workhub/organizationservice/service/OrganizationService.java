package net.workhub.organizationservice.service;

import net.workhub.organizationservice.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationByCode(String code);

    OrganizationDto updateOrganizationByCode(OrganizationDto organizationDto);

    void deleteOrganizationByCode(String code);

    List<OrganizationDto> getAllOrganizations();
}
