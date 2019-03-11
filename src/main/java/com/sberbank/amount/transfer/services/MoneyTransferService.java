package com.sberbank.amount.transfer.services;

import com.sberbank.amount.transfer.database.AccountsDAO;
import com.sberbank.amount.transfer.database.entities.AccountEntity;
import com.sberbank.amount.transfer.exceptions.*;
import com.sberbank.amount.transfer.controllers.models.TransferRequestModel;
import com.sberbank.amount.transfer.controllers.models.TransferResponseModel;
import com.sberbank.amount.transfer.services.validations.RequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MoneyTransferService {

    @Autowired
    private RequestValidation requestValidation;

    @Autowired
    private AccountsDAO accountsDAO;

    @Autowired
    private TransferResponseModel transferResponseModel;

    @Transactional
    public TransferResponseModel transfer(TransferRequestModel request) throws TransferException {
        requestValidation.isValidRequest(request);
        AccountEntity accountPostEntity = accountsDAO.getEntity(request.getAccountId());
        accountPostEntity = writeOffMoney(accountPostEntity, request.getAmount());
        AccountEntity accountReciveEntity = accountsDAO.getEntity(request.getDestinationAccountId());
        accountReciveEntity = addMoney(accountReciveEntity, request.getAmount());
        accountsDAO.update(accountPostEntity);
        accountsDAO.update(accountReciveEntity);
        transferResponseModel.init(0, "Средства успешно переведены", accountReciveEntity.getID());
        return transferResponseModel;
    }

    private AccountEntity writeOffMoney(AccountEntity accountEntity, double amount) throws NotEnoughMoneyException {
        double updatesAmount = accountEntity.getAmount() - amount;
        if(updatesAmount < 0 ){
            throw new NotEnoughMoneyException(String.format("На счете №: %s - Не достаточно средств для списания",
                    accountEntity.getID()));
        }
        accountEntity.setAmount(updatesAmount);
        return accountEntity;
    }

    private AccountEntity addMoney(AccountEntity accountEntity, double amount) {
        double updatesAmount = accountEntity.getAmount() + amount;
        accountEntity.setAmount(updatesAmount);
        return accountEntity;
    }
}
