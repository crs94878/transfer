package com.sberbank.amount.transfer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OperationNotWorkException extends TransferException {

    public OperationNotWorkException(String message){
        super(message);
    }
}
