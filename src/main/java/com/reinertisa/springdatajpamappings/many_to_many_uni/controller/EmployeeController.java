package com.reinertisa.springdatajpamappings.many_to_many_uni.controller;

import com.reinertisa.springdatajpamappings.many_to_many_uni.dto.EmployeeDto;
import com.reinertisa.springdatajpamappings.many_to_many_uni.service.EmployeeService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<Page<EmployeeDto>> getAllEmployees(
            @PageableDefault(size = 20, page = 0, sort = "name")Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees(pageable));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
