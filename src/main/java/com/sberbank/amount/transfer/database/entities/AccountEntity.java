package com.sberbank.amount.transfer.database.entities;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNTS")
@NamedNativeQueries({
        @NamedNativeQuery(name = "getAccount",
                query = "SELECT * FROM Accounts WHERE ID = :ID  ",
                resultClass = AccountEntity.class),
        @NamedNativeQuery(name = "updateAccountsAmount",
                query = "UPDATE ACCOUNTS SET AMOUNT = :AMOUNT WHERE ID = :ID",
                resultClass = AccountEntity.class)
})
public class AccountEntity {

    @Id
    @Column(name = "ID")
    private String ID;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "CURRENCY")
    private String currency;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
