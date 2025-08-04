package com.reinertisa.springdatajpamappings.one_to_one_uni.repository;

import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.AddressEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.firstName LIKE %:name% OR u.lastName LIKE %:name%")
    List<UserEntity> findByName(@Param("name") String name);

    Optional<UserEntity> findByAddressEntity(AddressEntity address);
}
