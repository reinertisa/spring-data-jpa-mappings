package com.reinertisa.springdatajpamappings.one_to_one_uni.mapper;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.AddressDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.AddressEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    private AddressMapper addressMapper;

    @BeforeEach
    void setUp() {
        addressMapper = new AddressMapper();
    }

    @Test
    void shouldMapAddressEntityToAddressDto() {
        // Given
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(1L);
        addressEntity.setCity("Sunnyvale");
        addressEntity.setState("CA");
        addressEntity.setCountry("USA");
        addressEntity.setZipCode(94085);

        // When
        AddressDto addressDto = addressMapper.apply(addressEntity);

        // Then
        assertEquals(addressEntity.getId(), addressDto.getId());
        assertEquals(addressEntity.getCity(), addressDto.getCity());
        assertEquals(addressEntity.getState(), addressDto.getState());
        assertEquals(addressEntity.getCountry(), addressDto.getCountry());
        assertEquals(addressEntity.getZipCode(), addressDto.getZipCode());
    }

    @Test
    void shouldThrowExceptionWhenAddressEntityIsNull() {
        Exception ex = assertThrows(NullPointerException.class, () -> addressMapper.apply(null));
        assertEquals("AddressEntity must not be null", ex.getMessage());
    }
}