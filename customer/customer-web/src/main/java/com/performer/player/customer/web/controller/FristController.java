package com.performer.player.customer.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FristController {

    @RequestMapping("index")
    public String pageInfo(HttpServletRequest request) {
        return "index";
    }
}
