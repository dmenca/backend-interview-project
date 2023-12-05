package com.dmenca.java.basic.proxy.dynamic;

import net.sf.cglib.proxy.Enhancer;

public class CglibTest {
    public static void main(String[] args) {
        Class clazz = AliSmsService.class;
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());

        AliSmsService aliSmsService = (AliSmsService)enhancer.create();
        aliSmsService.send("hhhh");
    }
}
