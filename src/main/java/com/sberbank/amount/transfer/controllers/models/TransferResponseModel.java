package com.sberbank.amount.transfer.controllers.models;

public class TransferResponseModel {
    private int statusCode;
    private String message;
    private String destinationAccountId;

    public void init(int statusCode, String message, String destinationAccountId){
        this.statusCode = statusCode;
        this.message = message;
        this.destinationAccountId = destinationAccountId;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
