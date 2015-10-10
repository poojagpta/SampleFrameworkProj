package com.jbksoft.dynamicProxyExample;

/**
 * Created by pooja on 10/9/15.
 */
public interface Account {
    int depositAmount(int accountNumber,int amount);
    int getBalance(int accountNumber);
    int calculateInterest(int accountNumber);
    int withdrawlAmount(int accountNumber,int amount);
}
