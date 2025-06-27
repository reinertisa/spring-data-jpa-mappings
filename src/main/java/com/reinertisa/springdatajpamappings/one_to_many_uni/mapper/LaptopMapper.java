package com.reinertisa.springdatajpamappings.one_to_many_uni.mapper;

import com.reinertisa.springdatajpamappings.one_to_many_uni.dto.LaptopDto;
import com.reinertisa.springdatajpamappings.one_to_many_uni.entity.LaptopEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LaptopMapper implements Function<LaptopEntity, LaptopDto> {

    @Override
    public LaptopDto apply(LaptopEntity laptop) {
        return LaptopDto.builder()
                .id(laptop.getId())
                .brand(laptop.getBrand())
                .model(laptop.getModel())
                .build();
    }
}
