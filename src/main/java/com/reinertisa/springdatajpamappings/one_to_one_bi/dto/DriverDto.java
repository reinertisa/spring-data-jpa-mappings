package com.reinertisa.springdatajpamappings.one_to_one_bi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    private Long id;
    private String fullName;
    private LicenseDto license;
}
