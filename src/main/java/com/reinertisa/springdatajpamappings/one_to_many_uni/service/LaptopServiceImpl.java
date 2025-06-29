package com.reinertisa.springdatajpamappings.one_to_many_uni.service;

import com.reinertisa.springdatajpamappings.one_to_many_uni.dto.LaptopDto;
import com.reinertisa.springdatajpamappings.one_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_many_uni.request.LaptopRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional(rollbackOn = Exception.class)
@Service
public class LaptopServiceImpl implements LaptopService {
    @Override
    public Page<LaptopDto> getAllLaptops(Pageable pageable) {
        return null;
    }

    @Override
    public LaptopDto getLaptopById(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public LaptopDto updateLaptop(Long id, LaptopRequest laptopRequest) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public List<LaptopDto> filterLaptops(String keyword) {
        return List.of();
    }
}
