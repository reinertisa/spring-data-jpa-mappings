package com.reinertisa.springdatajpamappings.one_to_one_bi.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.reinertisa.springdatajpamappings.one_to_one_bi.dto.LicenseDto;
import com.reinertisa.springdatajpamappings.one_to_one_bi.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_bi.request.LicenseRequest;
import com.reinertisa.springdatajpamappings.one_to_one_bi.service.LicenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/licenses")
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping("")
    public ResponseEntity<Page<LicenseDto>> getAllLicenses(
            @PageableDefault(size = 20, page = 0, sort = "state") Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(licenseService.getAllLicenses(pageable));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicenseDto> getLicenseById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(licenseService.getLicenseById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<LicenseDto> createLicense(@RequestBody @Valid LicenseRequest licenseRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(licenseService.createLicense(licenseRequest));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
