package com.rookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {


    @RequestMapping("hello")
    public String hello(){
        return "index";
    }


    @RequestMapping("json")
    @ResponseBody
    public Map<String, String> json(){
        Map<String,String> map = new HashMap<>();
        map.put("key","1");
        map.put("value","1");
        return map;
    }
}
