package com.reinertisa.springdatajpamappings.many_to_many_uni.service;

import com.reinertisa.springdatajpamappings.many_to_many_uni.dto.ProjectDto;
import com.reinertisa.springdatajpamappings.many_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.many_to_many_uni.request.ProjectRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    Page<ProjectDto> getAllProjects(Pageable pageable);

    ProjectDto getProjectById(Long id) throws ResourceNotFoundException;

    ProjectDto createProject(@Valid ProjectRequest projectRequest);

    ProjectDto updateProject(Long id, ProjectRequest projectRequest) throws ResourceNotFoundException;

    List<ProjectDto> filterProjects(String keyword);
}
