package com.reinertisa.springdatajpamappings.one_to_many_uni.controller;

import com.reinertisa.springdatajpamappings.one_to_many_uni.dto.LaptopDto;
import com.reinertisa.springdatajpamappings.one_to_many_uni.service.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/laptops")
public class LaptopController {
    private final LaptopService laptopService;

    @GetMapping("")
    public ResponseEntity<Page<LaptopDto>> getAllLaptops(
            @PageableDefault(size = 20, page = 0, sort = "brand")Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(laptopService.getAllLaptops(pageable));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
