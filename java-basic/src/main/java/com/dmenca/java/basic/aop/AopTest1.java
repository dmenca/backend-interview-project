package com.dmenca.java.basic.aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
/**
 * 基于 JDK 动态代理：当目标对象实现了接口时，Spring 会使用 JDK 动态代理来创建代理对象。JDK 动态代理要求目标对象实现至少一个接口，它会创建一个实现了目标对象接口的代理类，在方法调用时，通过 InvocationHandler 来拦截方法调用，实现横切逻辑。
 * 基于 CGLIB 的代理：当目标对象没有实现接口时，Spring 会使用 CGLIB 来创建代理对象。CGLIB（Code Generation Library）是一个功能强大的字节码生成库，它可以动态生成子类来代理目标对象，通过继承目标对象的方式来实现横切逻辑的织入。
 */
import java.lang.reflect.Method;

class TargetClass {
    public void doSomething() {
        System.out.println("Target method: doSomething()");
    }
}

// 切面类
class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Before advice: Before method " + method.getName());
    }
}
public class AopTest1 {

    public static void main(String[] args) {
        TargetClass targetClass = new TargetClass();
        // 使用基于JDK动态代理创建代理对象
        ProxyFactory proxyFactory = new ProxyFactory(targetClass);
        proxyFactory.addAdvice(new MyBeforeAdvice());
        proxyFactory.setProxyTargetClass(false); // 显示设置为使用 JDK 动态代理
        TargetClass proxyJDK = (TargetClass) proxyFactory.getProxy();
        proxyJDK.doSomething(); // 触发切面逻辑

        if (proxyJDK.getClass().getSuperclass() == TargetClass.class) {
            System.out.println("使用了 CGLIB 代理");
        } else {
            System.out.println("使用了 JDK 动态代理");
        }
        // 使用基于CGLIB的代理创建代理对象
        ProxyFactory proxyFactoryCGLIB = new ProxyFactory();
        proxyFactoryCGLIB.setProxyTargetClass(true); // 显示设置为使用 CGLIB 代理
        proxyFactoryCGLIB.setTarget(targetClass);
        TargetClass proxyCGLIB = (TargetClass) proxyFactoryCGLIB.getProxy();
        proxyCGLIB.doSomething(); // 触发切面逻辑
        if (proxyCGLIB.getClass().getSuperclass() == TargetClass.class) {
            System.out.println("使用了 CGLIB 代理");
        } else {
            System.out.println("使用了 JDK 动态代理");
        }
    }
}
