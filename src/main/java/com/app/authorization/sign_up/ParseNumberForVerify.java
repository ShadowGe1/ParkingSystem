package com.app.authorization.sign_up;

public class ParseNumberForVerify {

    protected boolean verify(String number) {
        //Verify +, 373 and contains 8 digits after 373
        if(number.charAt(0) == '+') {
            if(number.startsWith("373", 1)) {
                return number.substring(4).length() == 8;
            }
        }
        return false;
    }

}
