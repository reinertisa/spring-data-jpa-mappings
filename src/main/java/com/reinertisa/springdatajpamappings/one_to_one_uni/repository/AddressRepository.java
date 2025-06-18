package com.reinertisa.springdatajpamappings.one_to_one_uni.repository;

import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query("SELECT a from AddressEntity a WHERE a.city LIKE %:key% OR a.state LIKE %:key% OR a.country LIKE %:key%")
    List<AddressEntity> findByKeyword(@Param("key") String keyword);
}
