package com.performer.player.customer.service;

import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CreateTokenServiceImpl implements CreateTokenService{

    @Resource
    private RedissonClient redissonClient;

    public static final String USER_TOKEN_KEY = "USER_TOKEN";

    @Override
    public synchronized String generateToken(String user) {

        Map userTokenMap = redissonClient.getMap(USER_TOKEN_KEY);
        String uuid = String.format("%s:%s",UUID.randomUUID().toString(),user);

        if(userTokenMap == null){
            userTokenMap = new HashMap();
        }

        if(userTokenMap.containsKey(user)){
            userTokenMap.replace(user,uuid);
        }else{
            userTokenMap.put(user,uuid);
        }

        return uuid;

    }

    @Override
    public String getUserByToken(String token){

        if(StringUtil.isNotBlank(token)){
            Map userTokenMap = redissonClient.getMap(USER_TOKEN_KEY);
            if(userTokenMap != null){
                for(Object key : userTokenMap.keySet()){
                    if(token.equals(userTokenMap.get(key))){
                        return (String)key;
                    }
                }
            }
        }
        return "";
    }

}
