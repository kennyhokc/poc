package com.example.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DataException extends RuntimeException{

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super (message, cause);
    }
}
