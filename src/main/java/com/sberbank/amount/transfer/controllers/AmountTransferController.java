package com.sberbank.amount.transfer.controllers;

import com.sberbank.amount.transfer.exceptions.*;
import com.sberbank.amount.transfer.controllers.models.TransferRequestModel;
import com.sberbank.amount.transfer.controllers.models.TransferResponseModel;
import com.sberbank.amount.transfer.services.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class AmountTransferController {

    @Autowired
    MoneyTransferService moneyTransferService;

    @RequestMapping(value = "/transfer", method = RequestMethod.PUT)
    @ExceptionHandler({TransferException.class})
    public ResponseEntity<TransferResponseModel> transfer(@RequestBody TransferRequestModel transferRequest)
            throws NotEnoughMoneyException, OperationNotWorkException, AccountNotFoundException {
        TransferResponseModel transferResponse = moneyTransferService.transfer(transferRequest);
        return new ResponseEntity<>(transferResponse, HttpStatus.OK);
    }

}
