package com.reinertisa.springdatajpamappings.one_to_many_uni.controller;

import com.reinertisa.springdatajpamappings.one_to_many_uni.dto.LaptopDto;
import com.reinertisa.springdatajpamappings.one_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_many_uni.request.LaptopRequest;
import com.reinertisa.springdatajpamappings.one_to_many_uni.service.LaptopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<LaptopDto> getLaptopById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(laptopService.getLaptopById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<LaptopDto> createLaptop(@RequestBody @Valid LaptopRequest laptopRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(laptopService.createLaptop(laptopRequest));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaptopDto> updateLaptop(@RequestBody LaptopRequest laptopRequest, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(laptopService.updateLaptop(id, laptopRequest));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<LaptopDto>> filter(@RequestParam("keyword") String keyword) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(laptopService.filterLaptops(keyword));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
