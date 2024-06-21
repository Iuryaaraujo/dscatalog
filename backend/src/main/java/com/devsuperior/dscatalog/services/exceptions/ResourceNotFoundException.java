package com.devsuperior.dscatalog.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    // Exceção de recurso não encontrado
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
