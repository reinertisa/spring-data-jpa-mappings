package com.reinertisa.springdatajpamappings.one_to_many_uni.dto;

import com.reinertisa.springdatajpamappings.one_to_many_uni.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private List<LaptopDto> laptops;
}
