package com.reinertisa.springdatajpamappings.one_to_many_uni.repository;

import com.reinertisa.springdatajpamappings.one_to_many_uni.entity.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Long> {
    @Query("SELECT l from LaptopEntity l WHERE l.brand LIKE %:key% OR l.model LIKE %:key%")
    List<LaptopEntity> findByKeyword(@Param("key") String keyword);
}
