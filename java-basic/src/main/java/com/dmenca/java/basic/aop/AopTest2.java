package com.dmenca.java.basic.aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import javax.crypto.spec.PSource;
import java.lang.reflect.Method;
interface TargetInterface2 {
    void doSomething();
}
class TargetClass2 implements TargetInterface2{

    @Override
    public void doSomething() {
        System.out.println("Target method: doSomething()");
    }
}

// 切面类
class MyBeforeAdvice2 implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before advice: Before method " + method.getName());
    }
}
public class AopTest2 {

    public static void main(String[] args) {
        // 创建目标对象
        TargetClass2 target = new TargetClass2();

        // 使用默认代理方式创建代理对象（JDK 动态代理）
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // false明确设置为使用JDK动态代理 true为cglib动态代理
        proxyFactory.addAdvice(new MyBeforeAdvice2());
        TargetInterface2 proxy = (TargetInterface2) proxyFactory.getProxy();
        proxy.doSomething(); // 触发切面逻辑


        // 检查代理对象的类名是否包含 "$Proxy"
        System.out.println(proxy.getClass().getName());
        if (proxy.getClass().getName().contains("$Proxy")) {
            System.out.println("代理对象是由 JDK 动态代理生成的");
        } else {
            System.out.println("代理对象是由 CGLIB 代理生成的");
        }
    }
}
