package com.fimepay.merchantapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // or HttpStatus.CONFLICT
public class InvalidWalletStateException extends RuntimeException {

    public InvalidWalletStateException(String message) {
        super(message);
    }
}