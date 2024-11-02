package com.app.authorization.sign_up;

import com.app.authorization.passwordController.EncryptionPassword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionPasswordTest {
    EncryptionPassword encryptionPassword = new EncryptionPassword();

    @Test
    void cryptPasswordTestTrue() {
        assertTrue(encryptionPassword.checkPassword("Test123", generateEncryptedPassword1()));
    }

    @Test
    void cryptPasswordTestFalse() {
        assertFalse(encryptionPassword.checkPassword("Test123", generateEncryptedPassword2()));
    }

    private String generateEncryptedPassword1() {
        return encryptionPassword.cryptPassword("Test123");
    }

    private String generateEncryptedPassword2() {
        return encryptionPassword.cryptPassword("123Test");
    }
}