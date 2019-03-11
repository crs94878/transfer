package com.sberbank.amount.transfer.services.validations;

import com.sberbank.amount.transfer.exceptions.InvalidRequestException;
import com.sberbank.amount.transfer.controllers.models.TransferRequestModel;

public interface RequestValidation {
    boolean isValidRequest(TransferRequestModel request) throws InvalidRequestException;
}
