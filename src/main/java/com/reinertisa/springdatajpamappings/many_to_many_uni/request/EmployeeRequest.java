package com.reinertisa.springdatajpamappings.many_to_many_uni.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.reinertisa.springdatajpamappings.many_to_many_uni.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = false)
public class EmployeeRequest {
    @NotBlank(message = "Employee name is required.")
    private String name;
    @NotNull(message = "Role is required")
    private Role role;
    private List<ProjectRequest> projects;
}
