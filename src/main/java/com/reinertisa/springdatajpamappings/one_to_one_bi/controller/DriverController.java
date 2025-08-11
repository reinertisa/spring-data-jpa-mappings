package com.reinertisa.springdatajpamappings.one_to_one_bi.controller;

import com.reinertisa.springdatajpamappings.one_to_one_bi.dto.DriverDto;
import com.reinertisa.springdatajpamappings.one_to_one_bi.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_bi.request.DriverRequest;
import com.reinertisa.springdatajpamappings.one_to_one_bi.service.DriverService;
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
@RequestMapping("/api/v1/drivers")
public class DriverController {
    private final DriverService driverService;

    @GetMapping("")
    public ResponseEntity<Page<DriverDto>> getAllDrivers(
            @PageableDefault(size = 20, page = 0, sort = "fullName") Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(driverService.getAllDrivers(pageable));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(driverService.getDriverById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<DriverDto> createDriver(@RequestBody @Valid DriverRequest driverRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(driverService.createDriver(driverRequest));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDto> updateUser(@RequestBody DriverRequest driverRequest, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(driverService.updateDriver(id, driverRequest));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
