package com.dmenca.java.basic.proxy.statics;

import com.dmenca.java.basic.proxy.vo.UserVo;

/** UserController静态代理类 通过接口的方式扩展
 * @author caoan
 * @create 2022/3/1 23:44
 **/

public class UserControllerProxy implements IUserController{

    private UserController userController;

    public UserControllerProxy(UserController userController) {
        this.userController = userController;
    }

    @Override
    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        UserVo userVo = userController.login(telephone, password);
        long endTimestamp = System.currentTimeMillis();
        long responseTimestamp = endTimestamp - startTimestamp;
        System.out.println("login cost :"+responseTimestamp+" ms");
        return userVo;
    }

    @Override
    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        UserVo userVo = userController.register(telephone, password);
        long endTimestamp = System.currentTimeMillis();
        long responseTimestamp = endTimestamp - startTimestamp;
        System.out.println("register cost :"+responseTimestamp+" ms");
        return userVo;
    }

    public static void main(String[] args) {
        IUserController iUserController = new UserControllerProxy(new UserController());
    }
}
