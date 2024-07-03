package com.example.shoppingserver.globals.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyException extends RuntimeException {
    private String exceptionCode;
    private String exceptionMessage;

}
