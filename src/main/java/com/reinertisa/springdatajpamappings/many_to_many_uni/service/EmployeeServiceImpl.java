package com.reinertisa.springdatajpamappings.many_to_many_uni.service;

import com.reinertisa.springdatajpamappings.many_to_many_uni.dto.EmployeeDto;
import com.reinertisa.springdatajpamappings.many_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.many_to_many_uni.request.EmployeeRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Page<EmployeeDto> getAllEmployees(Pageable pageable) {
        return null;
    }

    @Override
    public EmployeeDto getEmployeeById(Long Id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeRequest employeeRequest) {
        return null;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeRequest employeeRequest) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void deleteStudent(Long id) throws ResourceNotFoundException {

    }

    @Override
    public List<EmployeeDto> filterByName(String name) {
        return List.of();
    }
}
