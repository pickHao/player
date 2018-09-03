package com.performer.player.comment.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.performer.player.comment.pojo.User;
import com.performer.player.comment.service.UserService;

@RestController
public class test {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    public List<User> getUserInfo() {
        List<User> user = userService.getAll();
        System.out.println(user.toString());
        return user;
    }

    @RequestMapping("/addUserInfo")
    public String addUserInfo() {
        User user = new User();
        user.setName("cwh");
        userService.insert(user);
        return "success:";
    }

}
