package br.dbserver.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidValueException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidValueException(String message) {
        super(message);
    }
}

