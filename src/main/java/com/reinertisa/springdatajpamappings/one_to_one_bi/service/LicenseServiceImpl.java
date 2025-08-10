package com.reinertisa.springdatajpamappings.one_to_one_bi.service;

import com.reinertisa.springdatajpamappings.one_to_one_bi.dto.LicenseDto;
import com.reinertisa.springdatajpamappings.one_to_one_bi.entity.LicenseEntity;
import com.reinertisa.springdatajpamappings.one_to_one_bi.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_bi.mapper.LicenseMapper;
import com.reinertisa.springdatajpamappings.one_to_one_bi.repository.LicenseRepository;
import com.reinertisa.springdatajpamappings.one_to_one_bi.request.LicenseRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository licenseRepository;
    private final LicenseMapper licenseMapper;

    @Override
    public Page<LicenseDto> getAllLicenses(Pageable pageable) {
        Page<LicenseEntity> licenses = licenseRepository.findAll(pageable);
        return licenses.map(licenseMapper);
    }

    @Override
    public LicenseDto getLicenseById(Long id) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "License ID must not be null");
        return licenseRepository.findById(id)
                .map(licenseMapper)
                .orElseThrow(() -> new ResourceNotFoundException("License not found for ID: " + id));
    }

    @Override
    public LicenseDto createLicense(@Valid LicenseRequest licenseRequest) {
        Optional<LicenseEntity> existingLicense = licenseRepository.findByLicenseNumberAndState(licenseRequest.getLicenseNumber(), licenseRequest.getState());

        // check for duplicates before saving
        existingLicense.ifPresent(existing -> {
            throw new ResourceNotFoundException("License with number" + existing.getLicenseNumber() +
                    " and state " + existing.getState() + " already. exists");
        });

        // Build and save new LicenseEntity
        LicenseEntity license = LicenseEntity.builder()
                .licenseNumber(licenseRequest.getLicenseNumber())
                .state(licenseRequest.getState())
                .build();

        return licenseMapper.apply(licenseRepository.save(license));
    }

    @Override
    public LicenseDto updateLicense(Long id, LicenseRequest licenseRequest) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public List<LicenseDto> filterLicenses(String keyword) {
        return List.of();
    }
}
