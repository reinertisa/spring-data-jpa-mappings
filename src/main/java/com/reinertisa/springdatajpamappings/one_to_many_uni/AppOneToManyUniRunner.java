package com.reinertisa.springdatajpamappings.one_to_many_uni;

import com.reinertisa.springdatajpamappings.one_to_many_uni.entity.LaptopEntity;
import com.reinertisa.springdatajpamappings.one_to_many_uni.entity.StudentEntity;
import com.reinertisa.springdatajpamappings.one_to_many_uni.enums.Gender;
import com.reinertisa.springdatajpamappings.one_to_many_uni.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class AppOneToManyUniRunner implements CommandLineRunner {

    private final StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {

        List<StudentEntity> students = IntStream.rangeClosed(0, 10)
                .mapToObj(index -> {
                    StudentEntity student = new StudentEntity();
                    student.setStudentId("S" + index);
                    student.setFirstName("FName - " + index);
                    student.setLastName("LName = " + index);
                    student.setEmail("test-" + index + "@gmail.com");
                    student.setGender(index % 2 == 0 ? Gender.MALE : Gender.FEMALE);

                    LaptopEntity laptop1 = new LaptopEntity();
                    laptop1.setBrand("Brand - " + index);
                    laptop1.setModel("Model - " + index);

                    LaptopEntity laptop2 = new LaptopEntity();
                    laptop2.setBrand("Brand - " + (index + 1));
                    laptop2.setModel("Model - " + (index + 1));

                    student.addLaptop(laptop1);
                    student.addLaptop(laptop2);

                    return student;
                }).toList();

        studentRepository.saveAll(students);

    }
}
