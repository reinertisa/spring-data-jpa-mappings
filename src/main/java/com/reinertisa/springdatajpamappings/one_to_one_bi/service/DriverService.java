package com.reinertisa.springdatajpamappings.one_to_one_bi.service;

import com.reinertisa.springdatajpamappings.one_to_one_bi.dto.DriverDto;
import com.reinertisa.springdatajpamappings.one_to_one_bi.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_bi.request.DriverRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DriverService {
    Page<DriverDto> getAllDrivers(Pageable pageable);

    DriverDto getDriverById(Long id) throws ResourceNotFoundException;

    DriverDto createDriver(@Valid DriverRequest driverRequest);

    DriverDto updateDriver(Long id, DriverRequest driverRequest) throws ResourceNotFoundException;

    void deleteDriver(Long id) throws ResourceNotFoundException;

    List<DriverDto> filterByName(String name);
}
