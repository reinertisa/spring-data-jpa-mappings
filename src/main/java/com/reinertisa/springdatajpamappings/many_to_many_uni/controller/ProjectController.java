package com.reinertisa.springdatajpamappings.many_to_many_uni.controller;

import com.reinertisa.springdatajpamappings.many_to_many_uni.dto.ProjectDto;
import com.reinertisa.springdatajpamappings.many_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.many_to_many_uni.request.ProjectRequest;
import com.reinertisa.springdatajpamappings.many_to_many_uni.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("")
    public ResponseEntity<Page<ProjectDto>> getAllProjects(
            @PageableDefault(size = 20, page = 0, sort = "projectName") Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProjects(pageable));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectService.getProjectById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectRequest projectRequest, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectService.updateProject(id, projectRequest));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProjectDto>> filter(@RequestParam("keyword") String keyword) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(projectService.filterProjects(keyword));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
