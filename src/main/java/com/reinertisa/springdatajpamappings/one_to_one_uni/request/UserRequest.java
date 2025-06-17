package com.reinertisa.springdatajpamappings.one_to_one_uni.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserRequest {
    @NotBlank(message = "First name is required.")
    private String firstName;
    @NotBlank(message = "Last name is required.")
    private String lastName;
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank
    private AddressRequest address;
}
