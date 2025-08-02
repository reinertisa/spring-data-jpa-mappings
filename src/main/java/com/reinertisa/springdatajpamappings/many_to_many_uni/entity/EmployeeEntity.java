package com.reinertisa.springdatajpamappings.many_to_many_uni.entity;

import com.reinertisa.springdatajpamappings.many_to_many_uni.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @ManyToMany(
            targetEntity = ProjectEntity.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    @Builder.Default
    private Set<ProjectEntity> projects = new HashSet<>();

    // helper methods
    public boolean addProject(ProjectEntity project) {
        return projects.add(project);
    }

    public boolean addAllProjects(Set<ProjectEntity> projectSet) {
        return projects.addAll(projectSet);
    }

    public boolean removeProject(ProjectEntity project) {
        return projects.remove(project);
    }

    public void removeAllProjects() {
        projects.clear();
    }
}
