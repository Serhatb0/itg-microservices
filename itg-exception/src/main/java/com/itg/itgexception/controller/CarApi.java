package com.itg.itgexception.controller;

import com.itg.itgexception.dto.Car;
import com.itg.itgexception.exception.EntityNotFoundException;
import com.itg.itgexception.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarApi {

    @Autowired
    private CarService carService;


    @GetMapping
    public ResponseEntity<Car> getCar(@RequestParam String name){
        //response entity ile ilgili bir hata alındığında
        // aşağıdaki yorumlu hale getirilmiş hata devreye girecek
        //carservice ile gili bir hata oluşursa
        // bu apidan dönüş yapılmadan önce
        // aşağıdaki yorum satırı haline getirilmiş metot devreye girer

        return ResponseEntity.ok(carService.getCar(name));
    }
    //bu annotation içine throwoble objeler verebilirsin
    //EntityNotFound ile ilgili bir hata alındığında bu metot çalışsın

   /* @ExceptionHandler({EntityNotFoundException.class})
    public String entityNotFound()
    {
        return "Kayıt Bulunamadı local";
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public String iaExceptionLocal()
    {
        return "yanlış parametre local";
    }*/

}

