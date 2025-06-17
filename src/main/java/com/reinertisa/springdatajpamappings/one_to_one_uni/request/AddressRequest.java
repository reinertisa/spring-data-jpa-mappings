package com.reinertisa.springdatajpamappings.one_to_one_uni.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressRequest {
    @NotBlank(message = "City name is required.")
    private String city;
    @NotBlank(message = "State name is required.")
    private String state;
    @NotBlank(message = "Country name is require.")
    private String country;
    @NotBlank(message = "Zip code is required.")
    private Integer zipCode;
}
