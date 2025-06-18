package com.reinertisa.springdatajpamappings.one_to_one_uni.service;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.AddressDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.AddressEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_uni.mapper.AddressMapper;
import com.reinertisa.springdatajpamappings.one_to_one_uni.repository.AddressRepository;
import com.reinertisa.springdatajpamappings.one_to_one_uni.request.AddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public Page<AddressDto> getAllAddresses(Pageable pageable) {
        Page<AddressEntity> addresses = addressRepository.findAll(pageable);
        return addresses.map(addressMapper);
    }

    @Override
    public AddressDto getAddressById(Long id) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "Address ID must not be null.");
        return addressRepository.findById(id)
                .map(addressMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for ID: " + id));
    }

    @Override
    public AddressDto updateAddress(Long id, AddressRequest addressRequest) throws ResourceNotFoundException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(addressRequest);

        AddressEntity address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for ID: " + id));

        Optional.ofNullable(addressRequest.getCity())
                .ifPresent(address::setCity);
        Optional.ofNullable(addressRequest.getState())
                .ifPresent(address::setState);
        Optional.ofNullable(addressRequest.getCountry())
                .ifPresent(address::setCountry);
        Optional.ofNullable(addressRequest.getZipCode())
                .ifPresent(address::setZipCode);

        return addressMapper.apply(addressRepository.save(address));
    }

    @Override
    public List<AddressDto> filterAddresses(String keyword) {
        return addressRepository.findByKeyword(keyword)
                .stream()
                .map(addressMapper)
                .toList();
    }
}
