package com.jbksoft.dynamicProxyExample;

/**
 * Created by pooja on 10/9/15.
 */
public class Driver {
    public static void main(String str[]){
        Account acct1=(Account) AccountProxy.newInstance(new SavingAccount());

        System.out.print(acct1.getBalance(100));

        Account acct2=(Account) AccountProxy.newInstance(new CheckingAccount());
        System.out.print(acct2.getBalance(100));

    }
}
