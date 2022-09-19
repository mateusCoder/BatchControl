package com.mateus.exceptions;

public class UnprocessableEntity extends RuntimeException{

    public UnprocessableEntity(String message){
        super(message);
    }
}
