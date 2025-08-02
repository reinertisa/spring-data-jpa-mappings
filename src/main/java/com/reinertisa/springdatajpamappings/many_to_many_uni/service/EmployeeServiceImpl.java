package com.reinertisa.springdatajpamappings.many_to_many_uni.service;

import com.reinertisa.springdatajpamappings.many_to_many_uni.dto.EmployeeDto;
import com.reinertisa.springdatajpamappings.many_to_many_uni.entity.EmployeeEntity;
import com.reinertisa.springdatajpamappings.many_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.many_to_many_uni.mapper.EmployeeMapper;
import com.reinertisa.springdatajpamappings.many_to_many_uni.repository.EmployeeRepository;
import com.reinertisa.springdatajpamappings.many_to_many_uni.repository.ProjectRepository;
import com.reinertisa.springdatajpamappings.many_to_many_uni.request.EmployeeRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional(rollbackOn = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final ProjectRepository projectRepository;

    @Override
    public Page<EmployeeDto> getAllEmployees(Pageable pageable) {
        Page<EmployeeEntity> employees = employeeRepository.findAll(pageable);
        return employees.map(employeeMapper);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "Employee ID must not be null");
        return employeeRepository.findById(id)
                .map(employeeMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for ID: " + id));
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
