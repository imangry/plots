package com.qunar.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("hello world");

        }
    }

    static class DynamicProxy implements InvocationHandler {

        Object originObject;

        Object bind(Object originObject) {
            this.originObject = originObject;
            return Proxy.newProxyInstance(originObject.getClass().getClassLoader(), originObject.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(proxy == originObject);
            System.out.println("welcome");
            return method.invoke(originObject, args);
        }
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);
        IHello bind = (IHello) new DynamicProxy().bind(new Hello());
        bind.sayHello();
    }
}
