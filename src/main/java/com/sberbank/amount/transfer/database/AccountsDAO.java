package com.sberbank.amount.transfer.database;

import com.sberbank.amount.transfer.database.entities.AccountEntity;
import com.sberbank.amount.transfer.exceptions.AccountNotFoundException;
import com.sberbank.amount.transfer.exceptions.OperationNotWorkException;
import com.sberbank.amount.transfer.exceptions.TransferException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class AccountsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = TransferException.class)
    public AccountEntity getEntity(String acountID) throws AccountNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        Query<AccountEntity> query = (Query<AccountEntity>) session.getNamedQuery("getAccount");
        query.setParameter("ID", acountID);
        List<AccountEntity> listAccounts = query.list();
        if(listAccounts.isEmpty()){
            throw new AccountNotFoundException(String.format("Счет №: %s - В базе не найден!", acountID));
        }
        AccountEntity accountEntity = listAccounts.get(0);
        System.out.println(accountEntity);
        return accountEntity;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = OperationNotWorkException.class)
    public void update(AccountEntity account){
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(account);
        }
        catch (Exception exception){
            exception.printStackTrace();
            throw new OperationNotWorkException("Операция не была корректно завершена");
        }
    }
}
