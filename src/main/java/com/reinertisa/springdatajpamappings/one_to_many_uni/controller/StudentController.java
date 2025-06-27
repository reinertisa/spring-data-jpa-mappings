package com.reinertisa.springdatajpamappings.one_to_many_uni.controller;

import com.reinertisa.springdatajpamappings.one_to_many_uni.dto.StudentDto;
import com.reinertisa.springdatajpamappings.one_to_many_uni.request.StudentRequest;
import com.reinertisa.springdatajpamappings.one_to_many_uni.service.StudentService;
import com.reinertisa.springdatajpamappings.one_to_one_uni.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("")
    public ResponseEntity<Page<StudentDto>> getAllStudents(
            @PageableDefault(size = 20, page = 0, sort = "studentId")Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents(pageable));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid StudentRequest studentRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentRequest));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

}
