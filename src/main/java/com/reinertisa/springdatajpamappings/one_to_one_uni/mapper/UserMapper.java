package com.reinertisa.springdatajpamappings.one_to_one_uni.mapper;

import com.reinertisa.springdatajpamappings.one_to_one_uni.dto.UserDto;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class UserMapper implements Function<UserEntity, UserDto> {
    private final AddressMapper addressMapper;

    @Override
    public UserDto apply(UserEntity userEntity) {

        Objects.requireNonNull(userEntity, "UserEntity must not be null");

        return UserDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .address(userEntity.getAddressEntity() != null ? addressMapper.apply(userEntity.getAddressEntity()) : null)
                .build();
    }
}
