package com.reinertisa.springdatajpamappings.many_to_many_uni.mapper;


import com.reinertisa.springdatajpamappings.many_to_many_uni.dto.EmployeeDto;
import com.reinertisa.springdatajpamappings.many_to_many_uni.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class EmployeeMapper implements Function<EmployeeEntity, EmployeeDto> {

    private final ProjectMapper projectMapper;

    @Override
    public EmployeeDto apply(EmployeeEntity employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .projects(employee.getProjects().stream().map(projectMapper).toList())
                .build();
    }
}
