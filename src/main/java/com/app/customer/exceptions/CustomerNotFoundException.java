package com.app.customer.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    private static final String ID_MESSAGE = "Customer %d not found";

    private static final String NAME_MESSAGE = "Customer %s not found";

    public CustomerNotFoundException(Integer id){
        super(ID_MESSAGE.formatted(id));
    }

    public CustomerNotFoundException(String name){
        super(NAME_MESSAGE.formatted(name));
    }
}
