package com.reinertisa.springdatajpamappings.one_to_one_bi.service;

import com.reinertisa.springdatajpamappings.one_to_one_bi.dto.LicenseDto;
import com.reinertisa.springdatajpamappings.one_to_one_bi.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_bi.request.LicenseRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LicenseService {

    Page<LicenseDto> getAllLicenses(Pageable pageable);

    LicenseDto getLicenseById(Long id) throws ResourceNotFoundException;

    LicenseDto createLicense(@Valid LicenseRequest licenseRequest);

    LicenseDto updateLicense(Long id, LicenseRequest licenseRequest) throws ResourceNotFoundException;

    List<LicenseDto> filterLicenses(String keyword);

}
