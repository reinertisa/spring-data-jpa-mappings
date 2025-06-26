package com.reinertisa.springdatajpamappings.one_to_many_uni.service;

import com.reinertisa.springdatajpamappings.one_to_many_uni.dto.StudentDto;
import com.reinertisa.springdatajpamappings.one_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_many_uni.request.StudentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public Page<StudentDto> getAllStudents(Pageable pageable) {
        return null;
    }

    @Override
    public StudentDto getStudentById(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public StudentDto createStudent(StudentRequest studentRequest) {
        return null;
    }

    @Override
    public StudentDto updateStudent(Long id, StudentRequest studentRequest) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void deleteStudent(Long id) throws ResourceNotFoundException {

    }

    @Override
    public List<StudentDto> filterByName(String name) {
        return List.of();
    }
}
