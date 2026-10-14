package com.example.authapi.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    private static final String USER_ALREADY_EXIST_TEXT = "User with username = %s already exist.";
    private static final String USER_ALREADY_EXIST_WITH_EMAIL_AND_USERNAME_TEXT = "User with username = %s and email = %s already exist.";

    public UserAlreadyExistsException(String username){
        super(String.format(USER_ALREADY_EXIST_TEXT, username));
    }

    public UserAlreadyExistsException(String email, String username){
        super(String.format(USER_ALREADY_EXIST_TEXT, username, email));
    }

}
