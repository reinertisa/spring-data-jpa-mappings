package com.reinertisa.springdatajpamappings.one_to_many_uni.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.reinertisa.springdatajpamappings.one_to_many_uni.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = false)
public class StudentRequest {
    @NotBlank(message = "Student id is required.")
    private String studentId;
    @NotBlank(message = "First name is required.")
    private String firstName;
    @NotBlank(message = "Last name is required.")
    private String lastName;
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email")
    private String email;
    @NotNull(message = "Gender is required.")
    private Gender gender;
}
