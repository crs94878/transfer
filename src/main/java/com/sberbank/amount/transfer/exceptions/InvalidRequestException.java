package com.sberbank.amount.transfer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends TransferException {
    public InvalidRequestException(String message){
        super(message);
    }
}
