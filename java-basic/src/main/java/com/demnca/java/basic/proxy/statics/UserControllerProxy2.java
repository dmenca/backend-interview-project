package com.demnca.java.basic.proxy.statics;

import com.demnca.java.basic.proxy.vo.UserVo;

/** 通过继承的方式扩展
 * @author caoan
 * @create 2022/3/1 23:52
 **/
public class UserControllerProxy2 extends UserController {

    @Override
    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        UserVo userVo = super.login(telephone, password);
        long endTimestamp = System.currentTimeMillis();
        long responseTimestamp = endTimestamp - startTimestamp;
        System.out.println("login cost :"+responseTimestamp+" ms");
        return userVo;
    }

    @Override
    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        UserVo userVo = super.register(telephone, password);
        long endTimestamp = System.currentTimeMillis();
        long responseTimestamp = endTimestamp - startTimestamp;
        System.out.println("register cost :"+responseTimestamp+" ms");
        return userVo;
    }

    public static void main(String[] args) {
        UserController userController = new UserControllerProxy2();
    }
}
