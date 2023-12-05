package com.dmenca.java.basic.proxy.dynamic;

public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:"  + message);
        return message;
    }
}
