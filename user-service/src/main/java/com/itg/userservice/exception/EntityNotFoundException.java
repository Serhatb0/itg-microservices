package com.itg.userservice.exception;

public class EntityNotFoundException  extends RuntimeException{

    public EntityNotFoundException(String param)
    {
        super(param);
    }

}
