package com.reinertisa.springdatajpamappings.one_to_many_uni.dto;

import com.reinertisa.springdatajpamappings.one_to_many_uni.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
}
