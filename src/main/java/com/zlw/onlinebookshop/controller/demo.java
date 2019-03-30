package com.zlw.onlinebookshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zlw
 *
 * @create 2019-03-27 16:35
 */
@Controller
@RequestMapping("test")
public class demo {
    @GetMapping("test")
    public String demo(){
        return "userlogin";
    }
}
