package com.rookie.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;

/**
 * 对所有的controller进行建议 ---使用面向切面编程
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public void  exception(){
        /**
         * 当controller抛出异常时,会执行下面的代码 可以执行异常的类型
         * 这里对具体的逻辑进行处理
         */
        System.out.println("恭喜您,获得异常一枚");
    }

    @ModelAttribute
    public void pre(){
        /**
         * 这个注解 会在当前类所有的方法执行前执行
         */
    }


    @ExceptionHandler(IOException.class)
    public void  IOexception(Model model){
        /**
         * 当controller抛出异常时,会执行下面的代码 可以执行异常的类型
         * 这里对具体的逻辑进行处理
         */
        model.addAttribute("msg","文件上传失败");
        System.out.println("上传文件是啊比");
    }
}
