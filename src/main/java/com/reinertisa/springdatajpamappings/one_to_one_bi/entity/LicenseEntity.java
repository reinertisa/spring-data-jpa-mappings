package com.reinertisa.springdatajpamappings.one_to_one_bi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "licenses",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"license_number", "state"})}
)
public class LicenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_number")
    private String licenseNumber;
    @Column(name = "state", nullable = false)
    private String state;

    @JsonBackReference
    @OneToOne(
            targetEntity = DriverEntity.class,
            mappedBy = "license",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private DriverEntity driver;
}
