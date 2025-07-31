package com.reinertisa.springdatajpamappings.one_to_many_uni.service;

import com.reinertisa.springdatajpamappings.one_to_many_uni.dto.LaptopDto;
import com.reinertisa.springdatajpamappings.one_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_many_uni.request.LaptopRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LaptopService {

    Page<LaptopDto> getAllLaptops(Pageable pageable);

    LaptopDto getLaptopById(Long id) throws ResourceNotFoundException;

    LaptopDto createLaptop(@Valid LaptopRequest laptopRequest);

    LaptopDto updateLaptop(Long id, LaptopRequest laptopRequest) throws ResourceNotFoundException;

    List<LaptopDto> filterLaptops(String keyword);
}
