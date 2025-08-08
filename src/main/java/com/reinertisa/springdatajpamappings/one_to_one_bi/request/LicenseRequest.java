package com.reinertisa.springdatajpamappings.one_to_one_bi.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = false)
public class LicenseRequest {
    @NotBlank(message = "License number is required.")
    private String licenseNumber;

    @NotBlank(message = "State is required.")
    private String state;
}
