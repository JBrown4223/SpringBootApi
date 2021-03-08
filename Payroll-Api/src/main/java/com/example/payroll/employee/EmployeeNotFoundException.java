package com.example.payroll.employee;

class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(String username) {
        super("Could not find employee " + username);
    }
}
