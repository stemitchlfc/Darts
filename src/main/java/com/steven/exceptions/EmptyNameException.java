package com.steven.exceptions;

public class EmptyNameException extends RuntimeException{
    public EmptyNameException(String errorMessage){
        super(errorMessage);
    }
}
