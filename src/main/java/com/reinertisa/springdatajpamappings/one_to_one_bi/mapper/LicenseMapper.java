package com.reinertisa.springdatajpamappings.one_to_one_bi.mapper;

import com.reinertisa.springdatajpamappings.one_to_one_bi.dto.LicenseDto;
import com.reinertisa.springdatajpamappings.one_to_one_bi.entity.LicenseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@Component
public class LicenseMapper implements Function<LicenseEntity, LicenseDto> {

    @Override
    public LicenseDto apply(LicenseEntity licenseEntity) {
        Objects.requireNonNull(licenseEntity, "LicenseEntity must not be null.");

        return LicenseDto.builder()
                .id(licenseEntity.getId())
                .licenseNumber(licenseEntity.getLicenseNumber())
                .state(licenseEntity.getState())
                .build();
    }
}
