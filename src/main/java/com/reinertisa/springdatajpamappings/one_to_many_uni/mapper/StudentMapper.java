package com.reinertisa.springdatajpamappings.one_to_many_uni.mapper;

import com.reinertisa.springdatajpamappings.one_to_many_uni.dto.StudentDto;
import com.reinertisa.springdatajpamappings.one_to_many_uni.entity.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class StudentMapper implements Function<StudentEntity, StudentDto> {

    private final LaptopMapper laptopMapper;

    @Override
    public StudentDto apply(StudentEntity student) {

        return StudentDto.builder()
                .id(student.getId())
                .studentId(student.getStudentId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .gender(student.getGender())
                .laptops(student.getLaptops().stream().map(laptopMapper).toList())
                .build();
    }
}
