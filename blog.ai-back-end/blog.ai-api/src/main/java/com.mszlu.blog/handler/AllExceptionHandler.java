package com.mszlu.blog.handler;

import com.mszlu.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


//Intercepting methods annotated with @Controller, AOP implementation
@ControllerAdvice
public class AllExceptionHandler {

    // Handle the exception of Exception.class
    @ExceptionHandler(Exception.class)
    @ResponseBody //return json data
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(-999,"System error");
    }

}
