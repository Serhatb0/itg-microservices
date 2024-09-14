package com.itg.itgexception.service;

import java.util.ArrayList;
import java.util.List;


import com.itg.itgexception.dto.Car;
import com.itg.itgexception.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;


import jakarta.annotation.PostConstruct;


@Service
public class CarService {

    private static List<Car> carList = new ArrayList<>();

    @PostConstruct
    public void init() {
        carList.add(new Car("Car A","Brand 1"));
        carList.add(new Car("Car B","Brand 2"));
        carList.add(new Car("Car C","Brand 3"));
    }

    public Car getCar(String name)
    {
        //gelen request 1 ile başlarsa hata versin
        if(name.startsWith("1")) {
            throw new IllegalArgumentException();
        }

        return carList.stream().filter(item -> item.getName().equals(name)).findFirst()
                .orElseThrow(()-> new EntityNotFoundException(name));
    }

}