package com.dmenca.spi;

public class Main {
    public static void main(String[] args) {
        LoggerService loggerService = LoggerService.getInstance();
        loggerService.info("info");
        loggerService.debug("debug");
    }
}
