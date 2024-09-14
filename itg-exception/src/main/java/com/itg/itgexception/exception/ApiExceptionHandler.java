package com.itg.itgexception.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    //tüm api lerde de EntityNotFound ile ilgili bir exception alındığında
    // burası devreye girecek
    @ExceptionHandler({EntityNotFoundException.class})
    public String entityNotFound()
    {
        return "Kayıt Bulunamadı Genel";//json da dönelübelir hata kodu şu açaklaması şu şeklinde
    }


    @ExceptionHandler({IllegalArgumentException.class})
    public String iaException()
    {
        return "yanlış parametre  Genel";
    }
}
