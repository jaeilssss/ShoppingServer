package com.example.shoppingserver.globals.base;

import com.example.shoppingserver.globals.Response;
import com.example.shoppingserver.globals.exception.MyException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BaseController {
    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Boolean> handleException(MyException e, HttpServletRequest request) {
        log.error("error code : " + e.getExceptionCode()+ "  error message : "+e.getExceptionMessage());
        return new Response<>(
                e.getExceptionCode(),
                e.getExceptionMessage(),
                false
        );
    }
}
