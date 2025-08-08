package com.reinertisa.springdatajpamappings.one_to_one_bi.mapper;

import com.reinertisa.springdatajpamappings.one_to_one_bi.dto.DriverDto;
import com.reinertisa.springdatajpamappings.one_to_one_bi.entity.DriverEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class DriverMapper implements Function<DriverEntity, DriverDto> {

    private final LicenseMapper licenseMapper;

    @Override
    public DriverDto apply(DriverEntity driverEntity) {

        Objects.requireNonNull(driverEntity, "DriverEntity must not be null");

        return DriverDto.builder()
                .id(driverEntity.getId())
                .fullName(driverEntity.getFullName())
                .license(driverEntity.getLicense() != null ? licenseMapper.apply(driverEntity.getLicense()) : null)
                .build();
    }
}
