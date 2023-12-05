# 代理模式
代理模式就是在不改变原有类（或者叫被代理类）代码的情况下，通过引入代理类来给原有类附加功能。 
使用代理对象来代替真实对象的访问，这样就可以在不修改原目标对象的前提下，提供额外的功能操作，扩展目标对象的功能。

## 静态代理：
### 定义 
 对目标对象的每个方法的增强都是手动完成，不灵活。接口一旦新增方法，目标对象和代理对象都要进行修改，需要对每个目标类单独写一个代理类。 使用的场景很少。
### 步骤
1. 定义一个接口及其实现类
2. 创建一个代理类实现同样的接口
3. 将目标对象注入到代理类中，然后在代理类的对应方法中调用目标对象的方法

## 动态代理
### 定义
  动态代理的实现更加灵活，不需要为每一个目标类都单独创建一个代理类，并且不一定要实现接口。
  动态代理是在运行时动态生成类字节码，并加载到JVM中。Spring AOP、RPC框架都使用了动态代理，在框架中应用广泛。
  动态代理分为JDK动态代理、CGLIB动态代理。

### JDK 动态代理
#### 定义
  jdk动态代理是结合Proxy类、InvocationHandler接口方式来进行代理.通过Proxy.newProxyInstance()方法来生成代理对象。
  jdk动态代理必须要有接口和实现类，不能没有接口。
```java
 public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
        throws IllegalArgumentException
    {
        ....
    }
```
创建代理对象方法的传入参数
* loader: 传入代理对象的类加载器，用于加载代理对象
* interfaces： 需要被代理实现的接口
* h：实现了InvocationHandler的对象


需要一个类实现InvocationHandler接口中的方法，在方法中自定义处理逻辑。当我们的动态代理对象调用一个方法时，这个方法的调用就会被转发到
实现InvocationHandler接口类的invoke方法来调用。
```java
public interface InvocationHandler {

    /**
     * 当你使用代理对象调用方法的时候实际会调用到这个方法
     */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable;
}
```
invoker方法的三个参数：
* proxy: 动态生成的代理类
* method： 调用的方法
* args： 当前方法的参数

### JDK动态代理使用步骤
* 定义一个接口以及实现类
* 自定义InvocationHandler并重写invoke方法，在invoke方法中要调用被代理类的原生方法
* 通过Proxy.newProxyInstance的方法创建代理对象

## CGLIB动态代理
 基于ASM的字节码生成库，允许我们在运行时对字节码进行修改和动态生成。CGLIB通过继承方式实现代理。
 在CGLIB动态代理机制中MethodInterceptor接口和Enhancer类是核心
 需要自定义MethodInterceptor方法并重写intercept方法，intercept用于拦截增强被代理类的方法。
 
```java
public interface MethodInterceptor
extends Callback{
    // 拦截被代理类中的方法
    public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args,MethodProxy proxy) throws Throwable;
}

```

* obj: 被代理的对象
* method：被拦截的方法
* args： 入参
* proxy： 用于调用原始的方法

### CGLIB使用步骤
* 定义一个类
* 自定义MethodInterceptor类，并重写intercept方法
* 通过Enhancer类的create()方法创建代理类


## JDK 动态代理和 CGLIB 动态代理对比
JDK 动态代理只能代理实现了接口的类或者直接代理接口，而 CGLIB 可以代理未实现任何接口的类。 另外， CGLIB 动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用，因此不能代理声明为 final 类型的类和方法。