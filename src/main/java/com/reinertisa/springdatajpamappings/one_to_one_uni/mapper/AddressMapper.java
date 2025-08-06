package com.reinertisa.springdatajpamappings.one_to_one_uni.mapper;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.AddressDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.AddressEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@Component
public class AddressMapper implements Function<AddressEntity, AddressDto> {
    @Override
    public AddressDto apply(AddressEntity addressEntity) {

        Objects.requireNonNull(addressEntity, "AddressEntity must not be null");

        return AddressDto.builder()
                .id(addressEntity.getId())
                .city(addressEntity.getCity())
                .state(addressEntity.getState())
                .country(addressEntity.getCountry())
                .zipCode(addressEntity.getZipCode())
                .build();
    }
}
