package com.reinertisa.springdatajpamappings.one_to_many_uni.entity;

import com.reinertisa.springdatajpamappings.one_to_many_uni.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false, unique = true)
    private String studentId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @OneToMany(
            targetEntity = LaptopEntity.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Student_FK1"))
    @Builder.Default
    private Set<LaptopEntity> laptops = new HashSet<>();

    public boolean addLaptop(LaptopEntity laptop) {
        Objects.requireNonNull(laptop);
        return laptops.add(laptop);
    }

    public boolean removeLaptop(LaptopEntity laptop) {
        Objects.requireNonNull(laptop);
        return laptops.remove(laptop);
    }

    public void addAllLaptops(Set<LaptopEntity> laptopList) {
        laptops.addAll(laptopList);
    }

    public void removeAllLaptops() {
        laptops.clear();
    }
}
