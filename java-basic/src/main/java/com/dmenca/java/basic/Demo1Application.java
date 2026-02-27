package com.dmenca.java.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                System.err.println("JVM 正在关闭...")));
        SpringApplication.run(Demo1Application.class,args);
    }
}
