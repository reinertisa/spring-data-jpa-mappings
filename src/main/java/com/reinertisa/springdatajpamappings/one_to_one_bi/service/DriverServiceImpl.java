package com.reinertisa.springdatajpamappings.one_to_one_bi.service;

import com.reinertisa.springdatajpamappings.one_to_one_bi.dto.DriverDto;
import com.reinertisa.springdatajpamappings.one_to_one_bi.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_bi.request.DriverRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class DriverServiceImpl implements DriverService {
    @Override
    public Page<DriverDto> getAllDrivers(Pageable pageable) {
        return null;
    }

    @Override
    public DriverDto getDriverById(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public DriverDto createDriver(DriverRequest driverRequest) {
        return null;
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
