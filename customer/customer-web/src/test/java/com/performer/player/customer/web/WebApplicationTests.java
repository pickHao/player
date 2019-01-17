package com.performer.player.customer.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    Redisson redisson;

    @Resource
    RedissonClient redissonClient;

    @Test
    public void contextLoads() {

        stringRedisTemplate.opsForValue().set("append","asss");
        String append = stringRedisTemplate.opsForValue().get("append");
        System.out.println("!!!!"+append);

    }

    @Test
    public void test01(){

        Map map = redissonClient.getMap("");


    }





}
