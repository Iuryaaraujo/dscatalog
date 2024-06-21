package com.devsuperior.dscatalog.resources.exceptione;

import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.EmailException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice // Essa anotetion vai permite que intecepte algumas exceções da camada controller
// Manipulador de Exceção de Recurso
public class ResouceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entidadeNaoEncontrada(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now()); // now Horário atual
        error.setStatus(HttpStatus.NOT_FOUND.value()); // 404 // value é para pegar o numero inteiro
        error.setError("Recurso não encontrado");
        error.setMessage(e.getMessage()); // pegara messagem que tá no CategoryService
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

    // tratando minhas exceção de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validacao(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // codigo 422 ENTIDADE IMPROCESSÁVEL
        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now()); // now Horário atual
        error.setStatus(status.value());
        error.setError("Exceção de validação");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage()); // pegar o nome do erro e a messagem do erro
        }

        return  ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<StandardError> email(EmailException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Email exception");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return  ResponseEntity.status(status).body(error);
    }
}
