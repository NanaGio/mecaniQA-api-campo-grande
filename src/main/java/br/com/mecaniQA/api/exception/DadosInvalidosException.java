package br.com.mecaniQA.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DadosInvalidosException extends RuntimeException {
    public DadosInvalidosException(String message) {
        super(message);
    }
}
