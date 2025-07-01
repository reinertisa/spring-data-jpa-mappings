package com.reinertisa.springdatajpamappings.one_to_many_uni.service;

import com.reinertisa.springdatajpamappings.one_to_many_uni.dto.LaptopDto;
import com.reinertisa.springdatajpamappings.one_to_many_uni.entity.LaptopEntity;
import com.reinertisa.springdatajpamappings.one_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_many_uni.mapper.LaptopMapper;
import com.reinertisa.springdatajpamappings.one_to_many_uni.repository.LaptopRepository;
import com.reinertisa.springdatajpamappings.one_to_many_uni.request.LaptopRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;
    private final LaptopMapper laptopMapper;

    @Override
    public Page<LaptopDto> getAllLaptops(Pageable pageable) {
        Page<LaptopEntity> laptops = laptopRepository.findAll(pageable);
        return laptops.map(laptopMapper);
    }

    @Override
    public LaptopDto getLaptopById(Long id) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "Laptop ID must not be null.");
        return laptopRepository.findById(id)
                .map(laptopMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Laptop not found for ID: " + id));
    }

    @Override
    public LaptopDto updateLaptop(Long id, LaptopRequest laptopRequest) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "Address ID must not be null");
        Objects.requireNonNull(laptopRequest, "Laptop request must not be null.");

        LaptopEntity laptop = laptopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laptop not found for ID: " + id));

        Optional.ofNullable(laptopRequest.getBrand())
                .ifPresent(laptop::setBrand);
        Optional.ofNullable(laptopRequest.getModel())
                .ifPresent(laptop::setModel);

        return laptopMapper.apply(laptopRepository.save(laptop));
    }

    @Override
    public List<LaptopDto> filterLaptops(String keyword) {
        Objects.requireNonNull(keyword, "Keyword must not be null.");
        return laptopRepository.findByKeyword(keyword)
                .stream()
                .map(laptopMapper)
                .toList();
    }
}
