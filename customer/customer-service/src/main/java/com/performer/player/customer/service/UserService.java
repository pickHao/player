package com.performer.player.customer.service;


import com.customer.dao.data.User;

public interface UserService {

    /**
     * 登录
     */
    User queryUserInfoByName(String account, String password);

}
