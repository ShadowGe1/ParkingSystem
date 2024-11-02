package com.app.authorization.sign_up;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParseNumberForVerifyTest {

    private static ParseNumberForVerify parseNumberForVerify;

    @BeforeAll
    static void beforeAll() {
        parseNumberForVerify = new ParseNumberForVerify();
    }

    @Test
    void verifyValidNumber() {
        assertTrue(parseNumberForVerify.verify(generateValidNumber()));
    }
    @Test
    void verifyInvalidNumberWithoutPlus() {
        assertFalse(parseNumberForVerify.verify(generateInvalidNumberWithoutPlus()));
    }
    @Test
    void verifyInvalidNumberWithout373() {
        assertFalse(parseNumberForVerify.verify(generateInvalidNumberWithout373()));
    }
    @Test
    void verifyInvalidNumberWith7Numbers() {
        assertFalse(parseNumberForVerify.verify(generateInvalidNumberWith7Number()));
    }

    private String generateValidNumber() {
        return "+37360420998";
    }

    private String generateInvalidNumberWithoutPlus() {
        return "37360420998";
    }

    private String generateInvalidNumberWithout373() {
        return "+360420998";
    }

    private String generateInvalidNumberWith7Number() {
        return "+3736042099";
    }
}