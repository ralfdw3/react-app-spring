package br.dbserver.project.infra;

import br.dbserver.project.exceptions.BadRequestException;
import br.dbserver.project.exceptions.InvalidValueException;
import br.dbserver.project.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFound(Exception ex){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpStatus).body(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity badRequest(Exception ex){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity badRequestCheck(Exception ex){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(ex.getMessage());
    }
}
