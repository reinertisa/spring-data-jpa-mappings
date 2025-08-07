package com.reinertisa.springdatajpamappings.one_to_one_uni.service;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.UserDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.UserEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.mapper.UserMapper;
import com.reinertisa.springdatajpamappings.one_to_one_uni.repository.AddressRepository;
import com.reinertisa.springdatajpamappings.one_to_one_uni.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldReturnAllUsers() {
        PageRequest pageable = PageRequest.of(0, 10);
        List<UserEntity> userEntities = List.of(new UserEntity());
        Page<UserEntity> userPage = new PageImpl<>(userEntities);
        UserDto userDto = new UserDto();

        // mock the calls
        when(userRepository.findAll(pageable)).thenReturn(userPage);
        when(userMapper.apply(any(UserEntity.class))).thenReturn(userDto);

        Page<UserDto> result = userService.getAllUser(pageable);

        assertEquals(1, result.getTotalElements());
        verify(userRepository).findAll(pageable);
        verify(userRepository, times(1)).findAll(pageable);

    }

}