package com.dmenca.java.basic.spi;
import com.dmenca.spi.Logger;
public class Logback implements Logger {
    @Override
    public void info(String message) {
        System.out.println("log back info message " + message);
    }

    @Override
    public void debug(String message) {
        System.out.println("log back debug message " + message);
    }
}
