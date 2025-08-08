package com.reinertisa.springdatajpamappings.one_to_one_bi.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = false)
public class DriverRequest {
    @NotBlank(message = "Full name is required.")
    private String fullName;

    private Long licenseId;
    @Valid
    private LicenseRequest license;
}
