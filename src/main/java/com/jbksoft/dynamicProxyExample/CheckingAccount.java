package com.jbksoft.dynamicProxyExample;

/**
 * Created by pooja on 10/9/15.
 */
public class CheckingAccount implements Account {

    public int depositAmount(int accountNumber,int amount) {
    return 600;
    }

    public int getBalance(int accountNumber) {
        return 700;
    }

    public int calculateInterest(int accountNumber) {
        return 800;
    }

    public int withdrawlAmount(int accountNumber,int amount) {
       return 900;
    }
}
