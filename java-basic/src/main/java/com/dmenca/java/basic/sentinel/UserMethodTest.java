package com.dmenca.java.basic.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class UserMethodTest {

    @SentinelResource(value = "testGetUser",blockHandler  = "blockHandlerForGetUser")
    public User getUserById(String id){
        throw new RuntimeException("getUserById failed");
    }

    public User blockHandlerForGetUser(String id, BlockException ex){
        return new User("admin");
    }

    public static void main(String[] args) {
        UserMethodTest userMethodTest = new UserMethodTest();
        User user = userMethodTest.getUserById("test");
        System.out.println(user);
    }
}
