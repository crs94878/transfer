package com.sberbank.amount.transfer.services.validations;

import com.sberbank.amount.transfer.exceptions.InvalidRequestException;
import com.sberbank.amount.transfer.controllers.models.TransferRequestModel;

public class Validation implements RequestValidation {
    @Override
    public boolean isValidRequest(TransferRequestModel request) throws InvalidRequestException {
        if(request.getAccountId().equals(request.getDestinationAccountId())){
            throw new InvalidRequestException(String.format("ID счета отправителя - %s должен отличаться от счета получателя - %s",
                    request.getAccountId(), request.getDestinationAccountId()));
        }
        if (request.getAmount() < 0 ){
            throw new InvalidRequestException(String.format("Сумма - %s долна быть положительной", request.getAmount()));
        }
        return true;
    }
}
