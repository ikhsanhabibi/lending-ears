package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class SignUpActivityTest {

    @Test
    public void isEmailValid() {
        boolean expected;
        String exampleemail = "testtest";
        SignUpActivity checkEmailValid = new SignUpActivity();
        expected = checkEmailValid.isEmailValid(exampleemail);
        assertFalse(expected);
    }
}