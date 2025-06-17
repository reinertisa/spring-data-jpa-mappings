package com.reinertisa.springdatajpamappings.one_to_one_uni.service;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.UserDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.UserEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_uni.mapper.UserMapper;
import com.reinertisa.springdatajpamappings.one_to_one_uni.repository.UserRepository;
import com.reinertisa.springdatajpamappings.one_to_one_uni.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public Page<UserDto> getAllUser(Pageable pageable) {
        Page<UserEntity> users = userRepository.findAll(pageable);
        return users.map(userMapper);
    }

    @Override
    public UserDto getUserById(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public UserDto createUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserDto updateUser(Long id, UserRequest userRequest) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void deleteUser(Long id) throws ResourceNotFoundException {

    }
}
