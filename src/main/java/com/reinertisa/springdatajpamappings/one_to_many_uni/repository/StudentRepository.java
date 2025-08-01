package com.reinertisa.springdatajpamappings.one_to_many_uni.repository;

import com.reinertisa.springdatajpamappings.one_to_many_uni.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query("SELECT s from StudentEntity s WHERE s.firstName LIKE %:name% OR s.lastName LIKE %:name%")
    List<StudentEntity> findByName(@Param("name") String name);


    @Query("SELECT s FROM StudentEntity s JOIN s.laptops l WHERE l.id = :laptopId")
    Optional<StudentEntity> findOwnerOfLaptop(@Param("laptopId") Long laptopId);
}
