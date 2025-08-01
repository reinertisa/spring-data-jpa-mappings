package com.reinertisa.springdatajpamappings.one_to_many_uni.service;

import com.reinertisa.springdatajpamappings.one_to_many_uni.dto.StudentDto;
import com.reinertisa.springdatajpamappings.one_to_many_uni.entity.LaptopEntity;
import com.reinertisa.springdatajpamappings.one_to_many_uni.entity.StudentEntity;
import com.reinertisa.springdatajpamappings.one_to_many_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_many_uni.mapper.StudentMapper;
import com.reinertisa.springdatajpamappings.one_to_many_uni.repository.LaptopRepository;
import com.reinertisa.springdatajpamappings.one_to_many_uni.repository.StudentRepository;
import com.reinertisa.springdatajpamappings.one_to_many_uni.request.StudentRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(rollbackOn = Exception.class)
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final LaptopRepository laptopRepository;

    @Override
    public Page<StudentDto> getAllStudents(Pageable pageable) {
        Page<StudentEntity> students = studentRepository.findAll(pageable);
        return students.map(studentMapper);
    }

    @Override
    public StudentDto getStudentById(Long id) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "Student ID must not be null.");
        return studentRepository.findById(id)
                .map(studentMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for ID: " + id));
    }

    @Override
    public StudentDto createStudent(@Valid StudentRequest studentRequest) {
        StudentEntity student = StudentEntity.builder()
                .studentId(studentRequest.getStudentId())
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();

        // Add existing laptops
        if (studentRequest.getExistingLaptopIds() != null) {
            for (Long id : studentRequest.getExistingLaptopIds()) {
                LaptopEntity existingLap = laptopRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Laptop not found for ID: " + id));

                Optional<StudentEntity> owner = studentRepository.findOwnerOfLaptop(id);
                if (owner.isPresent() && !owner.get().getId().equals(student.getId())) {
                    throw new IllegalStateException("Laptop ID " + id + " is already assigned to another student.");
                }

                student.addLaptop(existingLap);
            }
        }

        // Add new laptops
        if (studentRequest.getNewLaptops() != null) {
            Set<LaptopEntity> newLaptops = studentRequest.getNewLaptops().stream().map(l -> LaptopEntity.builder()
                    .brand(l.getBrand())
                    .model(l.getModel())
                    .build()).collect(Collectors.toSet());
            student.addAllLaptops(newLaptops);
        }

        return studentMapper.apply(studentRepository.save(student));
    }

    @Override
    public StudentDto updateStudent(Long id, StudentRequest studentRequest) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "Student ID must not be null.");
        Objects.requireNonNull(studentRequest, "Student request must not be null");

        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for ID: " + id));

        // TODO(reinertisa) - check student id if it is used by different student
        // TODO(reinertisa) - check email if it is used by different student
        Optional.ofNullable(studentRequest.getStudentId())
                .ifPresent(student::setStudentId);
        Optional.ofNullable(studentRequest.getFirstName())
                .ifPresent(student::setFirstName);
        Optional.ofNullable(studentRequest.getLastName())
                .ifPresent(student::setLastName);
        Optional.ofNullable(studentRequest.getEmail())
                .ifPresent(student::setEmail);
        Optional.ofNullable(studentRequest.getGender())
                .ifPresent(student::setGender);

        if (!studentRequest.getNewLaptops().isEmpty()) {
            student.removeAllLaptops();
            student.addAllLaptops(studentRequest.getNewLaptops().stream()
                    .map(l ->
                            LaptopEntity.builder()
                                    .brand(l.getBrand())
                                    .model(l.getModel())
                                    .build()
                    ).collect(Collectors.toSet()));
        }
        return studentMapper.apply(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "Student ID must not be null.");
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for ID:" + id));
        studentRepository.delete(student);
    }

    @Override
    public List<StudentDto> filterByName(String name) {
        return studentRepository.findByName(name)
                .stream()
                .map(studentMapper)
                .toList();
    }
}
