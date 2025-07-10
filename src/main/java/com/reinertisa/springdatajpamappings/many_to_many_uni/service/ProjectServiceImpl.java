package com.reinertisa.springdatajpamappings.many_to_many_uni.service;

import com.reinertisa.springdatajpamappings.many_to_many_uni.dto.ProjectDto;
import com.reinertisa.springdatajpamappings.many_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.many_to_many_uni.request.ProjectRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProjectServiceImpl implements ProjectService {
    @Override
    public Page<ProjectDto> getAllProjects(Pageable pageable) {
        return null;
    }

    @Override
    public ProjectDto getProjectById(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ProjectDto updateProject(Long id, ProjectRequest projectRequest) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public List<ProjectDto> filterProjects(String keyword) {
        return List.of();
    }
}
