package com.reinertisa.springdatajpamappings.one_to_one_uni.repository;

import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.AddressEntity;
import com.reinertisa.springdatajpamappings.one_to_one_uni.entity.UserEntity;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindUsersByName() {
        // Given
        UserEntity user1 = new UserEntity();
        user1.setFirstName("Isa");
        user1.setLastName("Reinert");
        user1.setEmail("test1@gmail.com");

        UserEntity user2 = new UserEntity();
        user2.setFirstName("Sade");
        user2.setLastName("Miller");
        user2.setEmail("test2@gmail.com");

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();

        // When
        List<UserEntity> rez = userRepository.findByName("Isa");

        // Then
        assertEquals(1, rez.size());
        assertEquals("Isa", rez.getFirst().getFirstName());
    }

    @Test
    void shouldFindByAddressEntity() {
        // Given
        AddressEntity address = new AddressEntity();
        address.setCity("Sunnyvale");
        address.setState("CA");
        address.setCountry("USA");
        address.setZipCode(94085);
        entityManager.persist(address);

        UserEntity user = UserEntity.builder()
                .firstName("Isa")
                .lastName("Reinert")
                .email("test@gmail.com")
                .addressEntity(address)
                .build();
        entityManager.persist(user);
        entityManager.flush();

        // When
        Optional<UserEntity> rez = userRepository.findByAddressEntity(address);

        // Then
        assertTrue(rez.isPresent());
        assertEquals("Isa", rez.get().getFirstName());
    }

    @Test
    void shouldSaveUser() {
        UserEntity user = UserEntity.builder()
                .firstName("Isa")
                .lastName("Reinert")
                .email("test@gmail.com")
                .build();

        UserEntity savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
        assertEquals("Isa", savedUser.getFirstName());
    }

    @Test
    void shouldFindAllUsers() {
        // Given
        UserEntity user1 = UserEntity.builder()
                .firstName("Isa")
                .lastName("Reinert")
                .email("test1@gmail.com")
                .build();

        UserEntity user2 = UserEntity.builder()
                .firstName("Sade")
                .lastName("Miller")
                .email("test2@gmail.com")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        // When
        List<UserEntity> users = userRepository.findAll();

        // Then
        assertThat(users)
                .hasSize(2)
                .extracting(UserEntity::getFirstName)
                .containsExactlyInAnyOrder("Isa", "Sade");

        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u -> "Isa".equals(u.getFirstName())));
        assertTrue(users.stream().anyMatch(u -> "Sade".equals(u.getFirstName())));
    }

    @Test
    void shouldFindUserById() {
        // Given
        UserEntity user = UserEntity.builder()
                .firstName("Isa")
                .lastName("Reinert")
                .email("test@gmail.com")
                .build();

        UserEntity savedUser = entityManager.persistAndFlush(user);

        // When
        Optional<UserEntity> foundUser = userRepository.findById(savedUser.getId());

        // Then (JUnit)
        assertTrue(foundUser.isPresent(), "User should be found by ID");

        // Then (AssertJ)
        assertThat(foundUser.get())
                .extracting(UserEntity::getFirstName, UserEntity::getLastName, UserEntity::getEmail)
                .containsExactly("Isa", "Reinert", "test@gmail.com");
    }

    @Test
    void shouldUpdateUser() {
        // Given: persist initial user
        UserEntity user = UserEntity.builder()
                .firstName("Isa")
                .lastName("Reinert")
                .email("test@gmail.com")
                .build();

        entityManager.persistAndFlush(user);

        // When: update user fields
        user.setFirstName("Sade");
        user.setLastName("Miller");
        user.setEmail("test2@gmail.com");
        userRepository.save(user); // save the updated user

        // Then: fetch updated user and verify changes
        UserEntity updatedUser = userRepository.findById(user.getId()).orElseThrow();

        // Then (JUnit)
        assertEquals("Sade", updatedUser.getFirstName());

        // Then (AssertJ)
        assertThat(updatedUser.getFirstName()).isEqualTo("Sade");
        assertThat(updatedUser.getLastName()).isEqualTo("Miller");
        assertThat(updatedUser.getEmail()).isEqualTo("test2@gmail.com");
    }

    @Test
    void shouldDeleteUser() {
        // Given: persist a user
        UserEntity user = UserEntity.builder()
                .firstName("Isa")
                .lastName("Reinert")
                .email("test@gmail.com")
                .build();
        entityManager.persistAndFlush(user);

        // When: delete the user
        userRepository.delete(user);
        userRepository.flush(); // flush to immediately changes

        // Then: verify user no longer exists
        boolean exists = userRepository.existsById(user.getId());

        // Then (JUnit)
        assertFalse(exists);

        // Then (AssertJ)
        assertThat(userRepository.existsById(user.getId())).isFalse();
    }
}