package com.dmenca.java.basic.proxy.dynamic;

import com.dmenca.java.basic.proxy.vo.Animal;
import com.dmenca.java.basic.proxy.vo.Cat;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DebugInvocationHandler implements InvocationHandler {

    private final Object target;

    public DebugInvocationHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("after method " + method.getName());
        return result;
    }

    public static void main(String[] args) {
        Cat realObject = new Cat();
        // 创建 InvocationHandler 对象并关联被代理对象
        DebugInvocationHandler debugInvocationHandler = new DebugInvocationHandler(realObject);
        // 创建代理对象
        Animal proxy = (Animal)Proxy.newProxyInstance(Cat.class.getClassLoader(),
               Cat.class.getInterfaces(),
                debugInvocationHandler);
        // 使用代理对象的方法
        proxy.speak();
    }
}
