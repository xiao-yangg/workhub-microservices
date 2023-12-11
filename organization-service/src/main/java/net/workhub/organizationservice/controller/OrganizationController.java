package net.workhub.organizationservice.controller;

import lombok.AllArgsConstructor;
import net.workhub.organizationservice.dto.OrganizationDto;
import net.workhub.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {

    private OrganizationService organizationService;

    // Save organization REST API
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    // Get organization REST API
    @GetMapping("{organization-code}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("organization-code") String organizationCode) {
        OrganizationDto organization = organizationService.getOrganizationByCode(organizationCode);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    // Update organization REST API
    @PutMapping("{organization-code}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("organization-code") String organizationCode,
                                                          @RequestBody OrganizationDto organizationDto) {
        organizationDto.setOrganizationCode(organizationCode);
        OrganizationDto updatedOrganization = organizationService.updateOrganizationByCode(organizationDto);
        return new ResponseEntity<>(organizationDto, HttpStatus.OK);
    }

    // Delete organization REST API
    @DeleteMapping("{organization-code}")
    public ResponseEntity<String> deleteOrganization(@PathVariable("organization-code") String organizationCode) {
        organizationService.deleteOrganizationByCode(organizationCode);
        return new ResponseEntity<>("Organization successfully deleted!", HttpStatus.OK);
    }

    // List organizations REST API
    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        List<OrganizationDto> organizations = organizationService.getAllOrganizations();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }
}