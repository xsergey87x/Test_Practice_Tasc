package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NoSuchElementException extends Exception{

    public NoSuchElementException(String message) {
        super(message);
        System.out.println(message);
    }
}
