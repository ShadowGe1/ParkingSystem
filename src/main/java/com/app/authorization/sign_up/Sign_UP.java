package com.app.authorization.sign_up;

import com.app.entity.User;

public class Sign_UP {
    private User user;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String phone;
    private String carNumber;

    public Sign_UP(String username, String firstName, String lastName, String email, String password, String confirmPassword, String phone, String carNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phone = phone;
        this.carNumber = carNumber;
    }

    private boolean verifyPassword() {
        return password.equals(confirmPassword);
    }

    private boolean verifyUsername() {
        // TODO Verify Username From DataBase
        return true;
    }

    private boolean verifyEmail() {
        ValidationEmail validEmail = new ValidationEmail();
        return validEmail.isEmailValid(email);
    }

    private String cryptPassword() {
        // TODO Encrypt the Password
        return "";
    }

    private boolean verifyPhone() {
        ParseNumberForVerify parseNumberForVerify = new ParseNumberForVerify();
        return parseNumberForVerify.verify(phone);
    }

    private boolean verifyCarNumber() {
        // TODO Verify Moldavian Car Number
        return true;
    }
}
