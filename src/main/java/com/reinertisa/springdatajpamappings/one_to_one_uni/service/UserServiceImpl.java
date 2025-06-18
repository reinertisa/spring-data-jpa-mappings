package com.reinertisa.springdatajpamappings.one_to_one_uni.service;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.UserDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.AddressEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.UserEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_uni.mapper.UserMapper;
import com.reinertisa.springdatajpamappings.one_to_one_uni.repository.UserRepository;
import com.reinertisa.springdatajpamappings.one_to_one_uni.request.UserRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
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
        Objects.requireNonNull(id, "User ID must not be null.");
        return userRepository.findById(id)
                .map(userMapper)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + id));
    }

    @Override
    public UserDto createUser(@Valid UserRequest userRequest) {
        UserEntity user = UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .build();

        AddressEntity address = AddressEntity.builder()
                .city(userRequest.getAddress().getCity())
                .state(userRequest.getAddress().getState())
                .country(userRequest.getAddress().getCountry())
                .zipCode(userRequest.getAddress().getZipCode())
                .build();
        user.setAddressEntity(address);
        return userMapper.apply(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long id, UserRequest userRequest) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "User ID must not be null.");
        Objects.requireNonNull(userRequest, "User request must not be null.");

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID:" + id));

        Optional.ofNullable(userRequest.getFirstName())
                .ifPresent(user::setFirstName);
        Optional.ofNullable(userRequest.getLastName())
                .ifPresent(user::setLastName);
        Optional.ofNullable(userRequest.getEmail())
                .ifPresent(user::setEmail);

        AddressEntity address = user.getAddressEntity();
        Optional.ofNullable(userRequest.getAddress().getCity())
                        .ifPresent(address::setCity);
        Optional.ofNullable(userRequest.getAddress().getState())
                        .ifPresent(address::setState);
        Optional.ofNullable(userRequest.getAddress().getCountry())
                        .ifPresent(address::setCountry);
        Optional.ofNullable(userRequest.getAddress().getZipCode())
                        .ifPresent(address::setZipCode);

        user.setAddressEntity(address);

        user = userRepository.save(user);

        return userMapper.apply(user);
    }

    @Override
    public void deleteUser(Long id) throws ResourceNotFoundException {
        Objects.requireNonNull(id, "User ID must not be null.");
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + id));
        userRepository.delete(user);
    }
}
