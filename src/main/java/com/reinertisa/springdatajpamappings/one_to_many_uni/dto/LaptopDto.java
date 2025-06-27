package com.reinertisa.springdatajpamappings.one_to_many_uni.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LaptopDto {
    private Long id;
    private String brand;
    private String model;
}
