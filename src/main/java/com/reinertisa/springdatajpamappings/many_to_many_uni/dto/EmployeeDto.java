package com.reinertisa.springdatajpamappings.many_to_many_uni.dto;

import com.reinertisa.springdatajpamappings.many_to_many_uni.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String name;
    private Role role;
    private List<ProjectDto> projects;
}
