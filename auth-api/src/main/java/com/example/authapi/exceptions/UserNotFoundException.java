package com.example.authapi.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final String USER_NOT_FOUND_BY_USERNAME_TEXT = "User with username:  %s, not found! ";
    private static final String USER_NOT_FOUND_BY_ID_TEXT = "User with username:  %s, not found! ";

    public UserNotFoundException(String username){
        super(String.format(USER_NOT_FOUND_BY_USERNAME_TEXT, username));
    }

    public UserNotFoundException(Long id){
        super(String.format(USER_NOT_FOUND_BY_ID_TEXT, id));
    }

}
