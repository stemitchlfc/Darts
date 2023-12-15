package com.steven.exceptions;

public class InvalidDartsAtDoubleException extends RuntimeException{
    public InvalidDartsAtDoubleException(String errorMessage){
        super(errorMessage);
    }
}
