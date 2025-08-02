package com.reinertisa.springdatajpamappings.many_to_many_uni;

import com.reinertisa.springdatajpamappings.many_to_many_uni.entity.EmployeeEntity;
import com.reinertisa.springdatajpamappings.many_to_many_uni.entity.ProjectEntity;
import com.reinertisa.springdatajpamappings.many_to_many_uni.enums.Role;
import com.reinertisa.springdatajpamappings.many_to_many_uni.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class AppManyToManyUniRunner implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) {
        List<EmployeeEntity> employees = IntStream.rangeClosed(0, 10)
                .mapToObj(index -> {
                    EmployeeEntity employee = new EmployeeEntity();
                    employee.setName("Name - " + index);
                    if (index % 2 == 0) {
                        employee.setRole(Role.ADMIN);
                    } else if (index % 3 == 0) {
                        employee.setRole(Role.MANAGER);
                    } else {
                        employee.setRole(Role.REGULAR);
                    }

                    ProjectEntity project1 = new ProjectEntity();
                    project1.setProjectName("ProjectA - " + index);

                    ProjectEntity project2 = new ProjectEntity();
                    project2.setProjectName("ProjectB - " + index);

                    employee.addProject(project1);
                    employee.addProject(project2);

                    return employee;
                }).toList();

        employeeRepository.saveAll(employees);
    }

}
