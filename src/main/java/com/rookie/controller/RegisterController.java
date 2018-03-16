package com.rookie.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/views")
public class RegisterController {

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> register(String  name,String password,String reallyName,@RequestParam("repeatSubmitToken") String token) {
        System.out.println(reallyName);
        System.out.println(token);
        Map map = new HashMap<String,String>();
        map.put("name",reallyName);
        return map;
    }
}
