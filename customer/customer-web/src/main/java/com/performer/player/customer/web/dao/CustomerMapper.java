package com.performer.player.customer.web.dao;

import com.performer.player.customer.web.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {

    User getUserInfo(@Param("account") String account, @Param("password") String password);

}
