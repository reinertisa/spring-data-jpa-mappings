package com.reinertisa.springdatajpamappings.one_to_one_bi.repository;

import com.reinertisa.springdatajpamappings.one_to_one_bi.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {
}
