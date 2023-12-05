package com.dmenca.java.basic.proxy.statics;

import com.dmenca.java.basic.proxy.vo.UserVo;

/**
 * @author caoan
 * @create 2022/3/1 23:43
 **/
public class UserController implements IUserController {
    @Override
    public UserVo login(String telephone, String password) {
        return null;
    }

    @Override
    public UserVo register(String telephone, String password) {
        return null;
    }
}
