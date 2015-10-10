package com.jbksoft.dynamicProxyExample;

/**
 * Created by pooja on 10/9/15.
 */
public class SavingAccount implements Account {

    public int depositAmount(int accountNumber,int amount) {
        getBalance(accountNumber);
       return 200;
    }

    public int getBalance(int accountNumber) {
       return 300;
    }

    public int calculateInterest(int accountNumber) {
        getBalance(accountNumber);
        return 400;
    }

    public int withdrawlAmount(int accountNumber,int amount) {

        return 500;
    }
}
