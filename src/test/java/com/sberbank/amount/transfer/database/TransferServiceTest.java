package com.sberbank.amount.transfer.database;


import com.sberbank.amount.transfer.controllers.models.TransferRequestModel;
import com.sberbank.amount.transfer.controllers.models.TransferResponseModel;
import com.sberbank.amount.transfer.database.entities.AccountEntity;
import com.sberbank.amount.transfer.services.MoneyTransferService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferServiceTest {

    @Autowired
    private MoneyTransferService moneyTransferService;

    @Autowired
    private AccountsDAO accountsDAO;

    @Test
    public void transferMoneyTest()throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        TransferRequestModel requestModel1 = new TransferRequestModel();
        requestModel1.setAccountId("QWER");
        requestModel1.setAmount(100);
        requestModel1.setDestinationAccountId("ASDF");
        TransferRequestModel transferRequestModel2 = new TransferRequestModel();
        transferRequestModel2.setAccountId("ASDF");
        transferRequestModel2.setAmount(100);
        transferRequestModel2.setDestinationAccountId("QWER");
        AccountEntity accountBeforeTest1 = accountsDAO.getEntity(requestModel1.getAccountId());
        AccountEntity accountBeforeTest2 = accountsDAO.getEntity(transferRequestModel2.getAccountId());
        List<Future<MoneyTransferService>> list = new ArrayList<>();
        for(int i =0; i<1000; i++) {
            Callable<MoneyTransferService> callable1 = new Callable<MoneyTransferService>() {
                @Override
                public MoneyTransferService call() throws Exception {
                    TransferResponseModel response = moneyTransferService.transfer(requestModel1);
                    System.out.println(response.getMessage());
                    return null;
                }
            };
            Callable<MoneyTransferService> callable2 = new Callable<MoneyTransferService>() {
                @Override
                public MoneyTransferService call() throws Exception {
                    TransferResponseModel response = moneyTransferService.transfer(transferRequestModel2);
                    System.out.println(response.getMessage());
                    return null;
                }
            };
            Future<MoneyTransferService> future = executorService.submit(callable1);
            Future<MoneyTransferService> future1 = executorService.submit(callable2);
            list.add(future);
            list.add(future1);
        }
        for(Future future : list){
            future.get();
        }
        AccountEntity accountAfterTest1 = accountsDAO.getEntity(requestModel1.getAccountId());
        AccountEntity accountAfterTest2 = accountsDAO.getEntity(transferRequestModel2.getAccountId());
        Assert.assertEquals(accountBeforeTest1.getAmount(), accountAfterTest1.getAmount(), 0);
        Assert.assertEquals(accountAfterTest2.getAmount(), accountAfterTest2.getAmount(), 0);

    }
}
