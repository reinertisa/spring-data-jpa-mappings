package com.reinertisa.springdatajpamappings.one_to_one_uni.mapper;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.AddressDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.AddressEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    private final AddressMapper addressMapper = new AddressMapper();

    @Test
    void testApply() {
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
        assertEquals(1L, addressDto.getId());
        assertEquals("Sunnyvale", addressDto.getCity());
        assertEquals("CA", addressDto.getState());
        assertEquals("USA", addressDto.getCountry());
        assertEquals(94085, addressDto.getZipCode());
    }
}