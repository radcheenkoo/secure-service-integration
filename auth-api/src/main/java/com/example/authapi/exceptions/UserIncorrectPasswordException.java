package com.example.authapi.exceptions;

public class UserIncorrectPasswordException extends Throwable{

    private static final String USER_INCORRECT_TEXT = "Incorrect password for user with username = %s.";

    public UserIncorrectPasswordException(String username){
        super(String.format(USER_INCORRECT_TEXT, username));
    }

}