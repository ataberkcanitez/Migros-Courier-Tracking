package com.ataberkcanitez.migroscouriertracking.domain.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreCreateException extends RuntimeException {
    private final String message;

    public StoreCreateException(String message) {
        super(message);
        this.message = message;
    }
}
