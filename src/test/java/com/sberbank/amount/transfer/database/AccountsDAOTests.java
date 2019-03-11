package com.sberbank.amount.transfer.database;

import com.sberbank.amount.transfer.database.entities.AccountEntity;
import com.sberbank.amount.transfer.exceptions.AccountNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsDAOTests {

    @Autowired
    private AccountsDAO accountsDAO;

    @Test (expected = AccountNotFoundException.class)
    public void getEntityTest(){
        accountsDAO.getEntity(null);
    }

    @Test
    public void getEntityTestCompare(){
        AccountEntity testAcc = accountsDAO.getEntity("QWER");
        Assert.assertNotNull(testAcc);
    }
}
