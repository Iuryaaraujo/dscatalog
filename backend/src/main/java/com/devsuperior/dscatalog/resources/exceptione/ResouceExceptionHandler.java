package com.devsuperior.dscatalog.resources.exceptione;

import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice // Essa anotetion vai permite que intecepte algumas exceções da camada controller
public class ResouceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entidadeNaoEncontrada(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now()); // now Horário atual
        error.setStatus(HttpStatus.NOT_FOUND.value()); // 404 // value é para pegar o numero inteiro
        error.setError("Recurso não encontrado");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> excecaoBancoDados(DatabaseException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now()); // now Horário atual
        error.setStatus(HttpStatus.BAD_REQUEST.value()); // 400 erro generico
        error.setError("Exceção de banco de dados");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}