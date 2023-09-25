package com.demnca.java.basic.proxy.dynamic;

import com.demnca.java.basic.proxy.vo.UserVo;

/**
 * @author caoan
 * @create 2022/3/1 23:42
 **/
public interface IUserController {
    /**
     * 登录
     * @param telephone
     * @param password
     * @return
     */
    UserVo login(String telephone, String password);

    /**
     * 注册
     * @param telephone
     * @param password
     * @return
     */
    UserVo register(String telephone, String password);
}
