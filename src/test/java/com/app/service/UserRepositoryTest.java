package com.app.service;

import com.app.authorization.passwordController.EncryptionPassword;
import com.app.config.ConfigBD;
import com.app.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;
    private SessionFactory sessionFactory;
    private EncryptionPassword encryptionPassword;

    @BeforeEach
    void setUp() {
        sessionFactory = ConfigBD.getSessionFactory();
        userRepository = new UserRepository(sessionFactory);
        encryptionPassword = new EncryptionPassword();
    }

    @AfterEach
    void tearDown() {
        sessionFactory.close();
    }

    @Test
    void saveUser() {
        User user = User.builder()
                .username("username123")
                .name("Andrei")
                .surname("Clima")
                .email("andrei@gmail.com")
                .password(encryptionPassword.cryptPassword("Test123"))
                .phone("+37360420998")
                .build();

        userRepository.saveOrUpdateUser(user);
    }

    @Test
    void getUserById() {
        User user = userRepository.getUserById(1);
        assertNotNull(user.getId());
    }
}
