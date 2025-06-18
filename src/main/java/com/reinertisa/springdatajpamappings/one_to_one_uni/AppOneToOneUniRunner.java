package com.reinertisa.springdatajpamappings.one_to_one_uni;

import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.AddressEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.UserEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class AppOneToOneUniRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        List<UserEntity> users = IntStream.range(0, 10)
                .mapToObj(index -> {
                    UserEntity user = new UserEntity();
                    user.setFirstName("First name - " + index);
                    user.setLastName("Last name - " + index);
                    user.setEmail("test-" + index + "@test.com");

                    AddressEntity address = new AddressEntity();
                    address.setCity("City - " + index);
                    address.setState("State - " + index);
                    address.setCountry("Country - " + index);
                    address.setZipCode(10000 + index);

                    user.setAddressEntity(address);

                    return user;
                }).toList();

        userRepository.saveAll(users);

    }
}
