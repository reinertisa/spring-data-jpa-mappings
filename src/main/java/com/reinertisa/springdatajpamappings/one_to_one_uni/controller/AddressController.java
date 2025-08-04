package com.reinertisa.springdatajpamappings.one_to_one_uni.controller;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.AddressDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_uni.request.AddressRequest;
import com.reinertisa.springdatajpamappings.one_to_one_uni.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("")
    public ResponseEntity<Page<AddressDto>> getAddresses(@PageableDefault(size = 20, page = 0, sort = "state") Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(addressService.getAllAddresses(pageable));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<AddressDto> createAddress(@RequestBody @Valid AddressRequest addressRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(addressService.createAddress(addressRequest));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressRequest addressRequest, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(addressService.updateAddress(id, addressRequest));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<AddressDto>> filter(@RequestParam("keyword") String keyword) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(addressService.filterAddresses(keyword));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
