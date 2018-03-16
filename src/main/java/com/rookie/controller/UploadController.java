package com.rookie.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class UploadController {
    @Autowired
    private MultipartResolver resolver;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        String prefix =  ((CommonsMultipartResolver) resolver).getFileItemFactory().getRepository().getAbsolutePath();
        String name = prefix +"/"+ file.getOriginalFilename();
        FileUtils.writeByteArrayToFile(new File(name),file.getBytes());
        return "ok";
    }
}
