package com.reinertisa.springdatajpamappings.many_to_many_uni.mapper;

import com.reinertisa.springdatajpamappings.many_to_many_uni.dto.ProjectDto;
import com.reinertisa.springdatajpamappings.many_to_many_uni.entity.ProjectEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProjectMapper implements Function<ProjectEntity, ProjectDto> {

    @Override
    public ProjectDto apply(ProjectEntity project) {
        return ProjectDto.builder()
                .id(project.getId())
                .projectName(project.getProjectName())
                .build();
    }
}
