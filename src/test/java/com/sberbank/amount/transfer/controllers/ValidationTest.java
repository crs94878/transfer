package com.sberbank.amount.transfer.controllers;

import com.sberbank.amount.transfer.controllers.models.TransferRequestModel;
import com.sberbank.amount.transfer.exceptions.InvalidRequestException;
import com.sberbank.amount.transfer.services.validations.RequestValidation;
import com.sberbank.amount.transfer.services.validations.Validation;
import org.junit.Before;
import org.junit.Test;

public class ValidationTest {

    private TransferRequestModel request;
    private TransferRequestModel requestNodestinitionId;
    private TransferRequestModel requestWithCompareId;
    private RequestValidation requestValidation;
    private TransferRequestModel requestWithNegativeAmount;

    @Before
    public void init(){
        requestValidation = new Validation();
        request = new TransferRequestModel();
        requestNodestinitionId = new TransferRequestModel();
        requestWithCompareId = new TransferRequestModel();
        requestWithNegativeAmount = new TransferRequestModel();
        request.setAmount(500);
        request.setDestinationAccountId("QWER");
        requestNodestinitionId.setAmount(500);
        requestNodestinitionId.setAccountId("QWER");
        requestWithCompareId.setAccountId("QWER");
        requestWithCompareId.setDestinationAccountId("QWER");
        requestWithCompareId.setAmount(100);
        requestWithNegativeAmount.setAccountId("QWER");
        requestWithNegativeAmount.setDestinationAccountId("ASDF");
        requestWithNegativeAmount.setAmount(-100);
    }

    @Test(expected = InvalidRequestException.class)
    public void validationNullAccId(){
        requestValidation.isValidRequest(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void validationNullDestinitionId(){
        requestValidation.isValidRequest(requestNodestinitionId);
    }

    @Test(expected = InvalidRequestException.class)
    public void requestWithCompareIdTest(){
        requestValidation.isValidRequest(requestWithCompareId);
    }

    @Test(expected = InvalidRequestException.class)
    public void negativeAmountTest(){
        requestValidation.isValidRequest(requestWithNegativeAmount);
    }

}
