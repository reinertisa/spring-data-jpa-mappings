package com.reinertisa.springdatajpamappings.many_to_many_uni.repository;

import com.reinertisa.springdatajpamappings.many_to_many_uni.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
