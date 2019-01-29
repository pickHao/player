package com.performer.player.customer.service;

public interface CreateTokenService {


    String generateToken(String user);

    /**
     * 通过前台传递的token参数
     * @param token
     * @return
     */
    String getUserByToken(String token);

}
