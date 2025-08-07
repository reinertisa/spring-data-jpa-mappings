package com.reinertisa.springdatajpamappings.one_to_one_uni.service;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.UserDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.AddressEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.UserEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.exception.ResourceNotFoundException;
import com.reinertisa.springdatajpamappings.one_to_one_uni.mapper.UserMapper;
import com.reinertisa.springdatajpamappings.one_to_one_uni.repository.AddressRepository;
import com.reinertisa.springdatajpamappings.one_to_one_uni.repository.UserRepository;
import com.reinertisa.springdatajpamappings.one_to_one_uni.request.UserRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

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

        Page<UserDto> rez = userService.getAllUser(pageable);

        assertEquals(1, rez.getTotalElements());
        verify(userRepository).findAll(pageable);
        verify(userRepository, times(1)).findAll(pageable);
    }

    @Test
    void shouldReturnUserById() {
        // Given
        Long id = 1L;
        UserEntity userEntity = new UserEntity();
        UserDto userDto = new UserDto();

        // mock the calls
        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        when(userMapper.apply(userEntity)).thenReturn(userDto);

        UserDto rez = userService.getUserById(id);

        assertNotNull(rez);
        verify(userRepository).findById(id);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void getUserByIdThrowsResourceNotfound() {
        Long id = 2L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(id));

        assertEquals("User not found for ID: " + id, ex.getMessage());
    }

    @Test
    void shouldCreateUserWithNewAddress() {
        UserRequest request = UserRequest.builder()
                .firstName("Isa")
                .lastName("Reinert")
                .email("test@gmail.com")
                .build();

        AddressEntity address = AddressEntity.builder()
                .city("Sunnyvale")
                .state("CA")
                .country("USA")
                .zipCode(94085)
                .build();

        UserEntity savedUser = UserEntity.builder()
                .id(1L)
                .firstName("Isa")
                .lastName("Reinert")
                .email("test@gmail.com")
                .addressEntity(address)
                .build();

        UserDto userDto = new UserDto();
        userDto.setId(1L);

        // mock the calls
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);
        when(userMapper.apply(savedUser)).thenReturn(userDto);

        UserDto rez = userService.createUser(request);

        assertEquals(1L, rez.getId());
        verify(userRepository).save(any(UserEntity.class));
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

}