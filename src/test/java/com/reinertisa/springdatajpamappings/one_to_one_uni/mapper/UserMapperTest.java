package com.reinertisa.springdatajpamappings.one_to_one_uni.mapper;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.AddressDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.UserDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.AddressEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserMapperTest {

    private UserMapper userMapper;
    private AddressMapper addressMapper;

    @BeforeEach
    void setUp() {
        addressMapper = mock(AddressMapper.class); // mock the dependency
        userMapper = new UserMapper(addressMapper);
    }

    @Test
    void shouldMapUserEntityToUserDto() {
        // Given
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(1L);
        addressEntity.setCity("Sunnyvale");
        addressEntity.setState("CA");
        addressEntity.setCountry("USA");
        addressEntity.setZipCode(94085);

        AddressDto addressDto = AddressDto.builder()
                .id(1L)
                .city("Sunnyvale")
                .country("USA")
                .zipCode(94085)
                .build();

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("Isa");
        userEntity.setLastName("Reinert");
        userEntity.setEmail("test@gmail.com");
        userEntity.setAddressEntity(addressEntity);

        when(addressMapper.apply(addressEntity)).thenReturn(addressDto);

        // When
        UserDto userDto = userMapper.apply(userEntity);

        // Then
        assertEquals(userEntity.getId(), userDto.getId());
        assertEquals(userEntity.getFirstName(), userDto.getFirstName());
        assertEquals(userEntity.getLastName(), userDto.getLastName());
        assertEquals(userEntity.getEmail(), userDto.getEmail());

        assertNotNull(userDto.getAddress());
        assertEquals(addressDto, userDto.getAddress());
        verify(addressMapper).apply(addressEntity);
    }

    @Test
    void shouldThrowExceptionWhenUserEntityIsNull() {
        Exception ex = assertThrows(NullPointerException.class, () -> userMapper.apply(null));
        assertEquals("UserEntity must not be null", ex.getMessage());
    }
}