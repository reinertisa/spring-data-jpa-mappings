package com.reinertisa.springdatajpamappings.one_to_one_uni.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddressDto {
    private Long id;
    private String city;
    private String state;
    private String country;
    private Integer zipCode;
}
