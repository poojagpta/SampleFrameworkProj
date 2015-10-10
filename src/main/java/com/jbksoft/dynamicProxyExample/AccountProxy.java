package com.jbksoft.dynamicProxyExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by pooja on 10/9/15.
 */
public class AccountProxy implements java.lang.reflect.InvocationHandler {

    Object obj;

    private static Logger logger = LoggerFactory.getLogger(AccountProxy.class);


    private AccountProxy(Object obj) {
       this.obj=obj;
    }

    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
                .getClass().getInterfaces(), new AccountProxy(obj));
    }


    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {

        Object result;
        try {
            logger.info("before method " + m.getName());
            long start = System.nanoTime();
            result = m.invoke(obj, args);
            long end = System.nanoTime();
            logger.info(String.format("%s took %d ns", m.getName(), (end-start)) );
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        } finally {
            logger.info("after method " + m.getName());
        }
        return result;

    }
}
