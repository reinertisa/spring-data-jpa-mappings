package com.reinertisa.springdatajpamappings.one_to_one_bi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LicenseDto {
    private Long id;
    private String licenseNumber;
    private String state;
}
