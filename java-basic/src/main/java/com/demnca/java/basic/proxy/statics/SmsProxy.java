package com.demnca.java.basic.proxy.statics;

public class SmsProxy implements SmsService{

    private final SmsService smsService;

    public SmsProxy(SmsService smsService){
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        System.out.println("before method send()");
        smsService.send(message);
        System.out.println("after method send()");
        return null;
    }

    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }
}
