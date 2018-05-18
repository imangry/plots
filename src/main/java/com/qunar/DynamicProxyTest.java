package com.qunar;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class DynamicProxyTest {

    public static void main(String[] args) {
        Pet aPet = new Dog();

        Pet proxy = (Pet) Proxy.newProxyInstance(Pet.class.getClassLoader(), Dog.class.getInterfaces(), new DynamicProxyHandler(aPet));
        proxy.doSomething("dddog");
    }
}

interface Pet {
    void doSomething(String arg);
}

class Dog implements Pet {
    public void doSomething(String arg) {
        System.out.println("I'm dog," + arg);
    }
}

class DynamicProxyHandler implements InvocationHandler {
    private Object proxyed;
    DynamicProxyHandler(Object proxyed) {
        this.proxyed = proxyed;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Proxy:" + proxy.getClass());
        System.out.println("Proxyed Class:" + proxyed.getClass());
        System.out.println("Method name:" + method);
        System.out.println("args" + Arrays.toString(args));
        return method.invoke(proxyed, args);
    }
}