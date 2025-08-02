package com.reinertisa.springdatajpamappings.many_to_many_uni.service;

import com.reinertisa.springdatajpamappings.many_to_many_uni.dto.EmployeeDto;
import com.reinertisa.springdatajpamappings.many_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.many_to_many_uni.request.EmployeeRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Page<EmployeeDto> getAllEmployees(Pageable pageable);

    EmployeeDto getEmployeeById(Long id) throws ResourceNotFoundException;

    EmployeeDto createEmployee(@Valid EmployeeRequest employeeRequest);

    EmployeeDto updateEmployee(Long id, EmployeeRequest employeeRequest) throws ResourceNotFoundException;

    void deleteStudent(Long id) throws ResourceNotFoundException;

    List<EmployeeDto> filterByName(String name);
}
