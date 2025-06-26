package com.reinertisa.springdatajpamappings.one_to_many_uni.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = false)
public class LaptopRequest {
    @NotBlank(message = "Brand is required.")
    private String brand;
    @NotBlank(message = "Model is required.")
    private String model;
}
