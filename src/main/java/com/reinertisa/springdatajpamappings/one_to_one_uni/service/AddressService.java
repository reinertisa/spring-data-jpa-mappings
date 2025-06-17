package com.reinertisa.springdatajpamappings.one_to_one_uni.service;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.AddressDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {

    Page<AddressDto> getAllAddresses(Pageable pageable);

    AddressDto getAddressById(Long id) throws ResourceNotFoundException;

    AddressDto updateAddress(Long id, @Valid AddressRequest addressRequest) throws ResourceNotFoundException;

    List<AddressDto> filterAddresses(String keyword);
}
