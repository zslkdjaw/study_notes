package com.study.controller.utils;

import lombok.Data;

@Data
public class R {
    private  Boolean flag;
    private Object data;
    private String msg;
    public R(){

    }
    public R(Boolean flag){
        this.flag = flag;
    }
    public R(Boolean flag,Object data){
        this.data = data;
        this.flag = flag;
    }
    public R(String msg){
        this.msg = msg;
    }
    public R(Boolean flag,String msg){
        this.flag=flag;
        this.msg = msg;
    }


}
