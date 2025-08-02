package com.reinertisa.springdatajpamappings.many_to_many_uni.service;

import com.reinertisa.springdatajpamappings.many_to_many_uni.dto.EmployeeDto;
import com.reinertisa.springdatajpamappings.many_to_many_uni.entity.EmployeeEntity;
import com.reinertisa.springdatajpamappings.many_to_many_uni.entity.ProjectEntity;
import com.reinertisa.springdatajpamappings.many_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.many_to_many_uni.mapper.EmployeeMapper;
import com.reinertisa.springdatajpamappings.many_to_many_uni.repository.EmployeeRepository;
import com.reinertisa.springdatajpamappings.many_to_many_uni.repository.ProjectRepository;
import com.reinertisa.springdatajpamappings.many_to_many_uni.request.EmployeeRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    public EmployeeDto createEmployee(@Valid EmployeeRequest employeeRequest) {
        EmployeeEntity employee = EmployeeEntity.builder()
                .name(employeeRequest.getName())
                .role(employeeRequest.getRole())
                .build();

        // Add existing projects
        if (employeeRequest.getExistingProjectIds() != null) {
            for (Long id : employeeRequest.getExistingProjectIds()) {
                ProjectEntity existingProject = projectRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Project not found for ID: " + id));

                employee.addProject(existingProject);
            }
        }

        // Add new projects
        if (employeeRequest.getNewProjects() != null) {
            Set<ProjectEntity> newProjects = employeeRequest.getNewProjects().stream().map(p -> ProjectEntity.builder()
                    .projectName(p.getProjectName())
                    .build())
                    .collect(Collectors.toSet());
            employee.addAllProjects(newProjects);
        }

        return employeeMapper.apply(employeeRepository.save(employee));
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
