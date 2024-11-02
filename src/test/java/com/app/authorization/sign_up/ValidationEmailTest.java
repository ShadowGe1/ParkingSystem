package com.app.authorization.sign_up;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationEmailTest {

    private static ValidationEmail validationEmail;
    @BeforeAll
    static void beforeClass() {
        validationEmail = new ValidationEmail();
    }

    @Test
    void verifyValidEmail() {
        assertTrue(validationEmail.isEmailValid(generateValidEmail()));
    }
    @Test
    void verifyInvalidEmailWithoutAron() {
        assertFalse(validationEmail.isEmailValid(generateInvalidEmailWithoutAron()));
    }
    @Test
    void verifyInvalidEmailWithoutHeader() {
        assertFalse(validationEmail.isEmailValid(generateInvalidEmailWithoutHeader()));
    }
    @Test
    void verifyInvalidEmailWithoutCom() {
        assertFalse(validationEmail.isEmailValid(generateInvalidEmailWithoutCom()));
    }
    @Test
    void verifyInvalidEmailWithoutDot() {
        assertFalse(validationEmail.isEmailValid(generateInvalidEmailWithoutDot()));
    }

    private String generateValidEmail() {
        return "hello@gmail.com";
    }

    private String generateInvalidEmailWithoutAron() {
        return "hellogmail.com";
    }

    private String generateInvalidEmailWithoutHeader() {
        return "@gmail.com";
    }

    private String generateInvalidEmailWithoutCom() {
        return "hello.asddf@gmail.";
    }

    private String generateInvalidEmailWithoutDot() {
        return "hello.asd@gmailcom";
    }
}