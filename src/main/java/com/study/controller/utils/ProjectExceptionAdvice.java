package com.study.controller.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Springmvc异常处理
//@ControllerAdvice
@RestControllerAdvice
public class ProjectExceptionAdvice {
    //拦截所有异常信息
    @ExceptionHandler
    public R doException(Exception e){
        //记录日志
        //通知运维
        //通知开发
        e.printStackTrace();
        return new R("服务器故障");
    }
}
