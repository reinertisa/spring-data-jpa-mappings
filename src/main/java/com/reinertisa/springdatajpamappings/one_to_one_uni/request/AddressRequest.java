package com.reinertisa.springdatajpamappings.one_to_one_uni.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = false)
public class AddressRequest {
    @NotBlank(message = "City name is required.")
    private String city;
    @NotBlank(message = "State name is required.")
    private String state;
    @NotBlank(message = "Country name is require.")
    private String country;
    @NotNull(message = "Zip code is required.")
    @Min(value = 10000, message = "Min value is 10000.")
    @Max(value = 99999, message = "Max value is 99999.")
    private Integer zipCode;
}
