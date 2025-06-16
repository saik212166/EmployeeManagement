package com.example.EmployeeManagement.Service;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message){
        super(message);
    }
}
