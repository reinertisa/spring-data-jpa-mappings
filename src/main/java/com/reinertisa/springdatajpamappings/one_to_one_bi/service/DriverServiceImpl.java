package com.reinertisa.springdatajpamappings.one_to_one_bi.service;

import com.reinertisa.springdatajpamappings.one_to_one_bi.dto.DriverDto;
import com.reinertisa.springdatajpamappings.one_to_one_bi.entity.DriverEntity;
import com.reinertisa.springdatajpamappings.one_to_one_bi.entity.LicenseEntity;
import com.reinertisa.springdatajpamappings.one_to_one_bi.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_bi.mapper.DriverMapper;
import com.reinertisa.springdatajpamappings.one_to_one_bi.repository.DriverRepository;
import com.reinertisa.springdatajpamappings.one_to_one_bi.repository.LicenseRepository;
import com.reinertisa.springdatajpamappings.one_to_one_bi.request.DriverRequest;
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
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final LicenseRepository licenseRepository;

    @Override
    public Page<DriverDto> getAllDrivers(Pageable pageable) {
        Page<DriverEntity> drivers = driverRepository.findAll(pageable);
        return drivers.map(driverMapper);
    }

    @Override
    public DriverDto getDriverById(Long id) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "Driver ID must not be null");
        return driverRepository.findById(id)
                .map(driverMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found for ID: " + id));
    }

    @Override
    public DriverDto createDriver(@Valid DriverRequest driverRequest) {
        DriverEntity driver = DriverEntity.builder()
                .fullName(driverRequest.getFullName())
                .build();

        if (driverRequest.getLicenseId() != null) {
            LicenseEntity existingLicense = licenseRepository.findById(driverRequest.getLicenseId())
                    .orElseThrow(() -> new ResourceNotFoundException("License not found for ID: " + driverRequest.getLicenseId()));

            if (existingLicense.getDriver() != null) {
                throw new RuntimeException("License is already used by another driver.");
            }
            driver.setLicense(existingLicense);
        } else if (driverRequest.getLicense() != null) {
            Optional<LicenseEntity> existingLicenseOpt = licenseRepository.findByLicenseNumberAndState(driverRequest.getLicense().getLicenseNumber(), driverRequest.getLicense().getState());

            if (existingLicenseOpt.isPresent()) {
                LicenseEntity existingLicense = existingLicenseOpt.get();
                if (existingLicense.getDriver() != null) {
                    throw new RuntimeException("License is already used by another driver.");
                }
                driver.setLicense(existingLicense);
            } else {
                LicenseEntity licenseEntity = LicenseEntity.builder()
                        .licenseNumber(driverRequest.getLicense().getLicenseNumber())
                        .state(driverRequest.getLicense().getState())
                        .build();
                driver.setLicense(licenseEntity);
            }
        }

        return driverMapper.apply(driverRepository.save(driver));
    }

    @Override
    public DriverDto updateDriver(Long id, DriverRequest driverRequest) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void deleteDriver(Long id) throws ResourceNotFoundException {

    }

    @Override
    public List<DriverDto> filterByName(String name) {
        return List.of();
    }
}
