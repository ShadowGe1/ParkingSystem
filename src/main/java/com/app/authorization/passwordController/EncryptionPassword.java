package com.app.authorization.passwordController;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionPassword {
    // Spring Security
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public String cryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public Boolean checkPassword(String password, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(password, encryptedPassword);
    }
}
