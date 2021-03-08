package com.example.payroll.user;

class UserNotFoundException extends RuntimeException {

    UserNotFoundException(String userNF) {
        super("Could not find user " + userNF);
    }
}
