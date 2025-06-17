package com.reinertisa.springdatajpamappings.one_to_one_uni.service;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.UserDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_uni.request.UserRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    Page<UserDto> getAllUser(Pageable pageable);

    UserDto getUserById(Long id) throws ResourceNotFoundException;

    UserDto createUser(@Valid UserRequest userRequest);

    UserDto updateUser(Long id, UserRequest userRequest) throws ResourceNotFoundException;

    void deleteUser(Long id) throws ResourceNotFoundException;
}
