package com.reinertisa.springdatajpamappings.one_to_many_uni.service;

import com.reinertisa.springdatajpamappings.one_to_many_uni.dto.StudentDto;
import com.reinertisa.springdatajpamappings.one_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_many_uni.request.StudentRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    Page<StudentDto> getAllStudents(Pageable pageable);

    StudentDto getStudentById(Long id) throws ResourceNotFoundException;

    StudentDto createStudent(@Valid StudentRequest studentRequest);

    StudentDto updateStudent(Long id, StudentRequest studentRequest) throws ResourceNotFoundException;

    void deleteStudent(Long id) throws ResourceNotFoundException;

    List<StudentDto> filterByName(String name);
}
