package com.jbksoft.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by pooja on 10/7/15.
 */
public class Example implements IExample{
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String printName(String name){

        this.name=name+"----->Got from method";
        return this.name;
    }

    public static void main(String[] str){

        //1 way of doing it.
        IExample example=new Example();
        System.out.print(example.printName("Test"));

        //2 way of doing it--using proxy
        final IExample example1=new Example();
        IExample proxy=(IExample)Proxy.newProxyInstance(example1.getClass().getClassLoader(),
                new Class[]{IExample.class},
                new InvocationHandler() {

                    public Object invoke(Object proxy, Method method,
                                         Object[] args) throws Throwable {
                        return method.invoke(example1, args);
                    }
                });

        System.out.print(proxy.printName("TestClass"));

    }

}
