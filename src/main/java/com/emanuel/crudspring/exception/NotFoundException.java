package com.emanuel.crudspring.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String mensagem, Exception e) {
        super(mensagem, e);
    }
}
