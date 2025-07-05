package com.reinertisa.springdatajpamappings.many_to_many_uni.repository;

import com.reinertisa.springdatajpamappings.many_to_many_uni.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
