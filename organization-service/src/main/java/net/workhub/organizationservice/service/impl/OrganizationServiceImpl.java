package net.workhub.organizationservice.service.impl;

import lombok.AllArgsConstructor;
import net.workhub.organizationservice.dto.OrganizationDto;
import net.workhub.organizationservice.entity.Organization;
import net.workhub.organizationservice.exception.ResourceNotFoundException;
import net.workhub.organizationservice.mapper.OrganizationMapper;
import net.workhub.organizationservice.repository.OrganizationRepository;
import net.workhub.organizationservice.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {

        Organization organization = organizationRepository.findByOrganizationCode(code).orElseThrow(
                () -> new ResourceNotFoundException("Organization", "code", code)
        );

        return OrganizationMapper.mapToOrganizationDto(organization);
    }

    @Override
    public OrganizationDto updateOrganizationByCode(OrganizationDto organizationDto) {

        Organization existingOrganization = organizationRepository.findByOrganizationCode(organizationDto.getOrganizationCode()).orElseThrow(
                () -> new ResourceNotFoundException("Organization", "code", organizationDto.getOrganizationCode())
        );

        existingOrganization.setOrganizationName(organizationDto.getOrganizationName());
        existingOrganization.setOrganizationDescription(organizationDto.getOrganizationDescription());

        Organization savedOrganization = organizationRepository.save(existingOrganization);

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public void deleteOrganizationByCode(String code) {

        Organization existingOrganization = organizationRepository.findByOrganizationCode(code).orElseThrow(
                () -> new ResourceNotFoundException("Organization", "code", code)
        );

        organizationRepository.delete(existingOrganization);
    }

    @Override
    public List<OrganizationDto> getAllOrganizations() {

        List<Organization> organizations = organizationRepository.findAll();

        return organizations.stream().map((OrganizationMapper::mapToOrganizationDto)).collect(Collectors.toList());
    }
}
