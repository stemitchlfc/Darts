package com.steven.exceptions;

public class InvalidScoreException extends RuntimeException{
    public InvalidScoreException(String errorMessage){
        super(errorMessage);
    }
}
