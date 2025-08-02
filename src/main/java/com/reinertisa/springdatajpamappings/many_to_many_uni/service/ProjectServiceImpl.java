package com.reinertisa.springdatajpamappings.many_to_many_uni.service;

import com.reinertisa.springdatajpamappings.many_to_many_uni.dto.ProjectDto;
import com.reinertisa.springdatajpamappings.many_to_many_uni.entity.ProjectEntity;
import com.reinertisa.springdatajpamappings.many_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.many_to_many_uni.mapper.ProjectMapper;
import com.reinertisa.springdatajpamappings.many_to_many_uni.repository.ProjectRepository;
import com.reinertisa.springdatajpamappings.many_to_many_uni.request.ProjectRequest;
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
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public Page<ProjectDto> getAllProjects(Pageable pageable) {
        Page<ProjectEntity> projects = projectRepository.findAll(pageable);
        return projects.map(projectMapper);
    }

    @Override
    public ProjectDto getProjectById(Long id) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "Project Id must not be null");
        return projectRepository.findById(id)
                .map(projectMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found for ID: " + id));
    }

    @Override
    public ProjectDto createProject(ProjectRequest projectRequest) {
        ProjectEntity project = ProjectEntity.builder()
                .projectName(projectRequest.getProjectName())
                .build();
        return projectMapper.apply(projectRepository.save(project));
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
