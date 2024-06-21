package com.devsuperior.dscatalog.resources.exceptione;

import java.util.ArrayList;
import java.util.List;

// Erro de validação
// tudo q tem no StandardError vai ter no ValidationError
public class ValidationError extends StandardError {

    // cria uma lista de fieldMessage
    private List<FieldMessage> errors = new ArrayList<>();

    // somete o get
    public List<FieldMessage> getErrors() {
        return errors;
    }

    // metódo publico para adicionar um elemento a essa lista
    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
