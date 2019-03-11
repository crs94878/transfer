package com.sberbank.amount.transfer.exceptions;

public abstract class TransferException extends RuntimeException {
    public TransferException(String message){
        super(message);
    }
}
