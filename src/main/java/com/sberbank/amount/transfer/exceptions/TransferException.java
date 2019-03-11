package com.sberbank.amount.transfer.exceptions;

public class TransferException extends RuntimeException {
    public TransferException(String message){
        super(message);
    }
}
