package com.dmenca.java.basic.proxy.dynamic;


import com.dmenca.java.basic.proxy.vo.Cat;
import com.dmenca.java.basic.proxy.vo.TargetObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test1 {
    public static void main(String[] args) throws Exception {

        // 获取 Class 对象的四种方式
        // 1. 知道具体类 通过类.class获取
        Class c1 = Cat.class;
        // 2. 通过Class.forName传入类全路径获取
        Class  c2 = Class.forName("com.dmenca.java.basic.proxy.vo.Cat");
        // 3. 通过对象实例的getClass方法获取
        Cat cat3 = new Cat();
        Class c3 = cat3.getClass();

        //4. 通过类加载器的方式获取，通过类加载器的方式不会对Class对象进行初始化，也就是不会执行初始化、静态代码块、静态对象函数
        Class c4 = ClassLoader.getSystemClassLoader().loadClass("com.dmenca.java.basic.proxy.vo.Cat");

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);

        // 通过Class对象来创建实例
        Class<?> targetClass = Class.forName("com.dmenca.java.basic.proxy.vo.TargetObject");
        TargetObject targetObject = (TargetObject) targetClass.newInstance();
        // 通过Class对象获取类中定义的所有方法
        Method[] declaredMethods = targetClass.getDeclaredMethods();
        for (Method method : declaredMethods){
            System.out.println(method.getName());
        }
        // 获取指定的方法
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject,"ca_test");

        // 获取指定的参数并对参数进行修改
        Field field = targetClass.getDeclaredField("value");
        field.setAccessible(true);
        field.set(targetObject,"CA");

        // 调用private方法
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);



    }
}
