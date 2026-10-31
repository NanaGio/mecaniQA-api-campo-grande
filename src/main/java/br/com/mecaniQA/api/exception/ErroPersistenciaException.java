package br.com.mecaniQA.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErroPersistenciaException extends RuntimeException {
    public ErroPersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
