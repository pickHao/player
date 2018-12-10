package com.customer.dao.mapper;


import com.customer.dao.data.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {

    User getUserInfo(@Param("account") String account, @Param("password") String password);

}
