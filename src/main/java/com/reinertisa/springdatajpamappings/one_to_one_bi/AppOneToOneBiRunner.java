package com.reinertisa.springdatajpamappings.one_to_one_bi;

import com.reinertisa.springdatajpamappings.one_to_one_bi.entity.DriverEntity;
import com.reinertisa.springdatajpamappings.one_to_one_bi.entity.LicenseEntity;
import com.reinertisa.springdatajpamappings.one_to_one_bi.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class AppOneToOneBiRunner implements CommandLineRunner {

    private final DriverRepository driverRepository;

    @Override
    public void run(String... args) throws Exception {

        List<DriverEntity> drivers = IntStream.range(0, 10)
                .mapToObj(index -> {
                    DriverEntity driverEntity = DriverEntity.builder()
                            .fullName("Full name - " + index)
                            .build();

                    LicenseEntity licenseEntity = LicenseEntity.builder()
                            .licenseNumber("LicenseNumber - " + index)
                            .state("State - " + index)
                            .build();

                    driverEntity.setLicense(licenseEntity);

                    return driverEntity;
                }).toList();

        driverRepository.saveAll(drivers);
    }
}
