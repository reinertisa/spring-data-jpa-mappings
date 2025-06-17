package com.reinertisa.springdatajpamappings.one_to_one_uni.repository;

import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
