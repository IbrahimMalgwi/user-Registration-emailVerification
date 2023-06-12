package com.ganzymalgwi.userregistrationemailverification.execption;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
