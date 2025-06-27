package com.reinertisa.springdatajpamappings.one_to_many_uni.repository;

import com.reinertisa.springdatajpamappings.one_to_many_uni.entity.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Long> {
}
